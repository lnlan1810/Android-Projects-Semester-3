package com.itis.firstapp.ui.fragment
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.R
import com.itis.firstapp.databinding.AddTaskFragmentBinding
import com.itis.firstapp.model.TaskDb
import com.itis.firstapp.model.entities.Task
import com.itis.firstapp.ui.MainActivity
import kotlinx.coroutines.*
import java.util.*

private const val REQUEST_CODE = 1

class AddTaskFragment : Fragment(R.layout.add_task_fragment) {

    private var binding: AddTaskFragmentBinding? = null
    private lateinit var taskDb: TaskDb
    private lateinit var client: FusedLocationProviderClient
    private var calendar: Calendar? = null
    private var currentTaskId: Int? = null
    private var longitude: Double? = null
    private var latitude: Double? = null
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        client = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddTaskFragmentBinding.bind(view)
        taskDb = (requireActivity() as MainActivity).taskDb

        binding?.apply {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                setNavigationOnClickListener {
                    returnToTasksFragment()
                }
                setOnMenuItemClickListener { onOptionsItemSelected(it) }
            }
            btnDate.setOnClickListener {
                showDatePicker()
            }
            doneBtn.setOnClickListener{
                saveTask()
            }
            btnGeolocation.setOnClickListener{
               setLocation()
            }
        }
        isTaskExists()
    }

    private fun setLocation(){
        if(checkPermissions() == true){
            getCurrentLocation()
        } else {
            requestPermissions(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), REQUEST_CODE)
        }
    }

    private fun checkPermissions(): Boolean? {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    == PackageManager.PERMISSION_GRANTED)
        }
        return null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == REQUEST_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation()
        }
        else {
            showMessage("Access to location denied")
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) or
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            client.lastLocation.addOnSuccessListener { location: Location? ->
                latitude = location?.latitude
                longitude = location?.longitude
                if (location != null){
                    showMessage( "Geolocation found")
                } else {
                    showMessage( "Geolocation not found. Please enable it to get all the features")
                }
            }
        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    private fun isTaskExists() {
        arguments?.getInt("TASK_ID")?.let {
            currentTaskId = it
            setTask(it)
        }
    }

    private fun setTask(id: Int) {
        scope.launch {
            val task = taskDb.taskDao().getTaskById(id)
            binding?.apply {
                etTitle.setText(task?.title)
                etDesc.setText(task?.description)
                task?.date?.let {
                    calendar = Calendar.getInstance()
                    calendar?.time = it
                }
            }
        }
    }

    private fun saveTask() {
        currentTaskId?.let {
           updateTask(it)
        }
        if (currentTaskId == null && isDataCorrect()) {
            addTask()
            showMessage("Task successfully added")
            returnToTasksFragment()
        }
    }

    private fun updateTask(id: Int) {
        if (isDataCorrect()) {
            scope.launch {updateData(id)}
            showMessage("Task successfully updated.")
            returnToTasksFragment()
        }
    }

    private suspend fun updateData(id: Int) {
        val task = taskDb.taskDao().getTaskById(id)
        binding?.apply {
            task?.let {
                it.title = etTitle.text.toString()
                it.description = etDesc.text.toString()
                calendar?.apply {
                    it.date = time
                }
                taskDb.taskDao().updateTask(task)
            }
        }
    }

    private fun addTask() {
        binding?.apply {
            val newTask = Task(
                0,
                etTitle.text.toString(),
                etDesc.text.toString(),
                calendar?.time,
                longitude,
                latitude
            )
            scope.launch {
                taskDb.taskDao().add(newTask)
            }
        }
    }

    private fun isDataCorrect(): Boolean {
        binding?.apply {
            if (etTitle.text.toString().isEmpty()) {
                showMessage("Add title")
                return false
            }
            if (etDesc.text.toString().isEmpty()) {
                showMessage("Add description")
                return false
            }
        }
        return true
    }

    private fun showDatePicker() {
        calendar = Calendar.getInstance()
        val datePickerFragment = DatePickerFragment()
        val supportFragmentManager = requireActivity().supportFragmentManager

        supportFragmentManager.setFragmentResultListener(
            "REQUEST_KEY",
            viewLifecycleOwner
        ) { resultKey, bundle ->
            if (resultKey == "REQUEST_KEY") {
                calendar?.timeInMillis = bundle.getLong("DATE")
            }
        }
        datePickerFragment.show(supportFragmentManager, "DatePickerDialog")
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun returnToTasksFragment() {
        findNavController().popBackStack(R.id.mainFragment, true)
        findNavController().navigate(R.id.mainFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        scope.cancel()
    }
}
