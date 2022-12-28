package com.itis.firstapp.model

import android.app.Activity
import androidx.room.Room

class DbCreator {

    fun initDB(activity: Activity) =  Room.databaseBuilder(activity, TaskDb::class.java, "todo_db")
        .build()
}
