package com.itis.firstapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itis.firstapp.model.dao.DateConverter
import com.itis.firstapp.model.dao.TaskDao
import com.itis.firstapp.model.entities.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
    abstract class TaskDb : RoomDatabase() {

        abstract fun taskDao(): TaskDao
    }
