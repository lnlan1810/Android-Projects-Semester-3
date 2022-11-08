package com.example.clock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val service = NotificationService(context)
        service.showNotification(context)
    }
}