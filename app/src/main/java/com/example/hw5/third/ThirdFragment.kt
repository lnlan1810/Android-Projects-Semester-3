package com.example.hw5.third

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw5.SpaceItemDecorator
import com.example.hw5.databinding.FragmentThirdBinding
import com.example.hw5.model.SongRespository.songs
import com.example.hw5.second.recycler.SingerListAdapter

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var songAdapter: SongAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongAdapter(songs, Glide.with(this))

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding) {
            rvHouses.run {
                adapter = songAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
        }
    }

}