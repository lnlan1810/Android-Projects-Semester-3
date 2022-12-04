package com.example.musicapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.databinding.FragmentMusicDetailsBinding
import com.example.musicapp.repository.MusicRepository
import com.example.musicapp.service.MusicService

class Music_detailsFragment : Fragment() {

    private lateinit var binding: FragmentMusicDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var binder: MusicService.MusicBinder? = null
    private var musicService: MusicService? = null
    private lateinit var music: Music

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            binder = service as? MusicService.MusicBinder
            musicService = binder?.getService()
            connectTrack()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
            binder = null
        }
    }

    private fun connectTrack() {
        val idSong = arguments?.getInt("idSong")
        idSong?.let {
            setData(idSong)
            playTrack(idSong)
        }
    }

    private fun playTrack(idSong: Int) {
        musicService?.currentTrack(idSong)

        with(binding) {
            ibPrev.setOnClickListener {
                musicService?.previousTrack()
                setData(musicService?.currentPosition)
                musicService?.play()
                ibPausePlay.setBackgroundResource(R.drawable.ic_pause)
            }
            ibNext.setOnClickListener {
                musicService?.nextTrack()
                setData(musicService?.currentPosition)
                musicService?.play()
                ibPausePlay.setBackgroundResource(R.drawable.ic_pause)
            }
            ibPausePlay.setOnClickListener {
                if (musicService?.isPlay() == true) {
                    musicService?.pause()
                    ibPausePlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
                }
                else {
                    musicService?.play()
                    ibPausePlay.setBackgroundResource(R.drawable.ic_pause)
                }
            }
        }
    }

    private fun setData(id: Int?) {
        id?.let{
            music = MusicRepository.musics[id]
        }
        with(binding) {
            tvTitle.text = music.title
            tvAuthor.text = music.author
            ivCover.setImageResource(music.cover)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.bindService(
            Intent(context, MusicService::class.java),
            connection,
            Context.BIND_AUTO_CREATE
        )
    }
}