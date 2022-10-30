package com.example.hw5.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw5.SpaceItemDecorator
import com.example.hw5.databinding.FragmentSecondBinding
import com.example.hw5.model.Singer
import com.example.hw5.model.SingerRespository.singers
import com.example.hw5.second.recycler.SingerListAdapter

class SecondFragment : Fragment() {

    private lateinit var  binding: FragmentSecondBinding
    private lateinit var  singerListAdapter: SingerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singerListAdapter = SingerListAdapter(Glide.with(this)) {
            singers.remove(it)
            singerListAdapter.submitList(singers)
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(singerListAdapter))
        itemTouchHelper.attachToRecyclerView(binding.rvSinger)

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding) {
            rvSinger.run {
                adapter = singerListAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            fabRefresh.setOnClickListener {
                showDialog()
            }
        }
        singerListAdapter.submitList(singers)
    }

    private var title:String? = null
    private var desc:String? = null
    private var pos:String? = null

    private fun showDialog() {
        SingerDialog.show(
            childFragmentManager,
            positive = {
                title = it[0]
                desc = it[1]
                pos = it[2]
                updateData()
            }
        )
    }

    private fun updateData() {
        if (pos.equals("")) {
            addItem(singers.size)
        }
        else {
            pos?.also { pos->
                val posInt = Integer.parseInt(pos)
                if (posInt<0 || posInt>=singers.size)
                    addItem(singers.size)
                else
                    addItem(posInt)
            }
        }
        singerListAdapter.submitList(singers)
    }

    private fun addItem(position: Int) {
        val defaultWindow = "https://upload.wikimedia.org/wikipedia/ru/thumb/5/54/Microsoft_Windows_XP_Logo.svg/1200px-Microsoft_Windows_XP_Logo.svg.png"
        title?.let { title ->
            desc?.let { desc ->
                singers.add(position, Singer(title, desc, defaultWindow))
            }
        }
    }

}