package com.example.taskapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 2)
abstract class DataBase : RoomDatabase() {
    //DAO
    abstract fun taskDao(): TaskDao
}