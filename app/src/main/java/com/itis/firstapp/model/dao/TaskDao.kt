package com.itis.firstapp.model.dao

import androidx.room.*
import com.itis.firstapp.model.entities.Task
import java.util.*

@Dao
interface TaskDao {

    @Insert
    suspend fun add(task: Task)

    @Query("SELECT * FROM task")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM task WHERE id=:task")
    suspend fun deleteTask(task: Int)

    @Query("DELETE FROM task")
    suspend fun deleteAll()
}
