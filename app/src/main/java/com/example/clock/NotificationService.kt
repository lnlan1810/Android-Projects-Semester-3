package com.example.clock

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import androidx.core.app.NotificationCompat

private const val CHANNEL_ID = "channel_id_1"

class NotificationService(
    context: Context
) {

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val audio by lazy {
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
    }

    private val pattern = arrayOf(100L, 200L, 0, 400L).toLongArray()

    @SuppressLint("ResourceType")
    fun showNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                "First channel",
                IMPORTANCE_HIGH
            ).apply {
                description = "description about my channel"
                lightColor = Color.BLUE
                vibrationPattern = pattern

                val sound: Uri = context.getSoundUri(R.raw.pipi)
                setSound(sound, audio)
            }.also {
                manager.createNotificationChannel(it)
            }
        }


        val intent = Intent(context, WakeupActivity::class.java).let {
            PendingIntent.getActivities(
                context,
                123,
                arrayOf(it),
                PendingIntent.FLAG_ONE_SHOT
            )
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_timer)
            .setContentTitle("It's time to wakeup")
            .setShowWhen(true)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(intent)
            .setContentText("wakeup wakeup")

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            with (builder) {
                setLights(Color.RED, 100, 100)
                setVibrate(pattern)
            }
        }
        manager.notify(1, builder.build())
    }

    private fun Context.getSoundUri(
        @RawRes id: Int
    ) = Uri.parse("android.resource://${packageName}/$id")
}
