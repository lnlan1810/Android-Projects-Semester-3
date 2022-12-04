package com.example.musicapp.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.musicapp.Music
import com.example.musicapp.repository.MusicRepository

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    var currentPosition: Int = 0
    private var musicBinder: MusicBinder = MusicBinder()
    private var tracks: List<Music> = MusicRepository.musics
    private lateinit var notificationService:NotificationService

    inner class MusicBinder : Binder() {
        //give service
        fun getService(): MusicService = this@MusicService
    }


    override fun onCreate() {
        super.onCreate()
        notificationService = NotificationService(this)
        notificationService.setNotification(currentPosition)
    }

    override fun onBind(intent: Intent): IBinder = musicBinder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "PREVIOUS" -> {previousTrack()}
            "PAUSE" -> {if (mediaPlayer.isPlaying)
                mediaPlayer.pause()
            else mediaPlayer.start()
            }
            "NEXT" -> {nextTrack()}
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    fun previousTrack() {
        if (currentPosition == 0) {
            currentPosition = tracks.size - 1
        }
        else currentPosition --

        currentTrack(currentPosition)
    }

    fun nextTrack() {
        if (currentPosition == tracks.size - 1) {
            currentPosition = 0
        }
        else currentPosition ++

        currentTrack(currentPosition)
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun play() {
        mediaPlayer.start()
    }

    fun isPlay() = mediaPlayer.isPlaying

    fun currentTrack(selectedTrackId: Int) {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, tracks[selectedTrackId].song)
        currentPosition = selectedTrackId
        mediaPlayer.start()

        notificationService.setNotification(selectedTrackId)
    }
}
