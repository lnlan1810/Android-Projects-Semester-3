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
import com.example.hw5.model.DataItem
import com.example.hw5.model.Repository.singers
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
        val defaultWindow = "data:image/jpeg;base64,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"
        title?.let { title ->
            desc?.let { desc ->
                singers.add(position, DataItem.Singer(title, desc, defaultWindow))
            }
        }
    }

}