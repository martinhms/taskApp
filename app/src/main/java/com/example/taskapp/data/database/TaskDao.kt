package com.example.taskapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query(value = "SELECT * from TaskEntity")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert
    fun addTask(item: TaskEntity)
}