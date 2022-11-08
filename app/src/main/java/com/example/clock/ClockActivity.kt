package com.example.clock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clock.databinding.ActivityClockBinding
import java.util.*

class ClockActivity: AppCompatActivity() {
    private lateinit var binding: ActivityClockBinding
    private var service: NotificationService? = null
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        service = NotificationService(this)

        var hour: String
        var minute: String

        binding.btnStart.setOnClickListener {
            hour = binding.etHour.text.toString()
            minute = binding.etMinute.text.toString()
            setTime(hour, minute)

            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            intent = Intent(this, Receiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }

        binding.btnStop.setOnClickListener {
//            intent = Intent(this, Receiver::class.java)
//            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun setTime(hour: String, minute: String) {
        calendar = Calendar.getInstance()
        calendar.time = Date(System.currentTimeMillis())
        calendar.timeInMillis = System.currentTimeMillis()
        if (hour != "") {
            calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
        }
        if (minute != "") {
            calendar.set(Calendar.MINUTE, minute.toInt())
        }
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }
}
