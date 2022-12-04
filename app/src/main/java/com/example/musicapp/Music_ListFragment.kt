package com.example.musicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.FragmentMusicListBinding
import com.example.musicapp.recyclerview.MusicAdapter
import com.example.musicapp.repository.MusicRepository

class Music_ListFragment : Fragment() {
   private lateinit var binding: FragmentMusicListBinding
   private  lateinit var  musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        musicAdapter = MusicAdapter(MusicRepository.musics) {
            showSelectedTrack(it)
        }
        binding.rvMusics.run {
            adapter = musicAdapter
            addItemDecoration(decorator)
            addItemDecoration(spacing)
        }
    }

    private fun showSelectedTrack(idSong: Int) {
        val bundle = Bundle().apply {
            putInt("idSong", idSong)
        }
        findNavController().navigate(R.id.action_track_ListFragment_to_track_detailsFragment, bundle)
    }

}