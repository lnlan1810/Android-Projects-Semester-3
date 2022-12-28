package com.itis.firstapp.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.R
import com.itis.firstapp.databinding.TasksFragmentBinding
import com.itis.firstapp.ui.MainActivity
import com.itis.firstapp.model.TaskDb
import com.itis.firstapp.model.entities.Task
import com.itis.firstapp.ui.recycler_view.SpaceItemDecorator
import com.itis.firstapp.ui.recycler_view.TaskAdapter
import kotlinx.coroutines.*
import java.util.*

class TasksFragment : Fragment(R.layout.tasks_fragment) {

    private val LIST_VIEW = "LIST_VIEW"
    private val GRID_VIEW = "GRID_VIEW"
    var currentView = GRID_VIEW
    private var binding: TasksFragmentBinding? = null
    private var taskAdapter: TaskAdapter? = null
    private lateinit var taskDb: TaskDb
    private lateinit var tasks: List<Task>
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar)

        binding = TasksFragmentBinding.bind(view)
        taskDb = (requireActivity() as MainActivity).taskDb

        taskAdapter = TaskAdapter({ showTaskFragment(it) }, { deleteTask(it) })

        binding!!.switchDarkMode.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                // Light Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                // Dark Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        gridView()

        binding?.apply {
            toolbar.setOnMenuItemClickListener {
                onOptionsItemSelected(it)
            }
            addBtn.setOnClickListener {
                showTaskFragment(null)
            }
            rvTasks.run {
                adapter = taskAdapter
                addItemDecoration(SpaceItemDecorator(context))
            }
        }

        scope.launch(Dispatchers.Default) { updateTasks() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_tasks -> {
                deleteAllTasks()
                true
            }
            R.id.change ->{
                changeView()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun changeView(){
        if(currentView == GRID_VIEW){
            listView()
        }
        else{
            gridView()
        }
    }

    private fun gridView(){
        currentView = GRID_VIEW
        binding?.rvTasks?.layoutManager = GridLayoutManager( context , 2 )
        binding?.rvTasks?.adapter = TaskAdapter({ showTaskFragment(it) }, { deleteTask(it) })
    }

    private fun listView(){
        currentView = LIST_VIEW
        binding?.rvTasks?.layoutManager = LinearLayoutManager( context)
        binding?.rvTasks?.adapter = TaskAdapter({ showTaskFragment(it) }, { deleteTask(it) })
    }
    private fun updateTasks() {
        scope.launch {  tasks = taskDb.taskDao().getAll()
            binding?.apply {
                if (tasks.isEmpty()) {
                    emptyTasks.visibility = View.VISIBLE
                    rvTasks.visibility = View.GONE
                } else {
                    emptyTasks.visibility = View.GONE
                    rvTasks.visibility = View.VISIBLE
                }
            }
            taskAdapter?.submitList(ArrayList(tasks)) }
    }

    private fun deleteAllTasks() {
        if(binding?.rvTasks?.visibility == View.VISIBLE) {
            AlertDialog.Builder(requireContext())
                .setMessage("Are you sure to delete all tasks?")
                .setPositiveButton("Yes") {
                        dialog, _ ->
                    scope.launch { taskDb.taskDao().deleteAll()
                    updateTasks()}
                    showMessage("All tasks are removed")
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") {
                        dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } else
            binding?.let {
               showMessage("You have no tasks")
            }
    }

    private fun deleteTask(id: Int) {
        scope.launch { taskDb.taskDao().deleteTask(id)
            updateTasks()}
            showMessage("deleted! ")
    }

    private fun showTaskFragment(id: Int?) {
        var bundle: Bundle? = null
        id?.also {
            bundle = Bundle().apply {
                putInt("TASK_ID", id)
            }
        }
        findNavController().navigate(
            R.id.action_mainFragment_to_taskFragment,
            bundle
        )
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        scope.cancel()
    }

}
