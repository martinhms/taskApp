package com.example.taskapp.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.taskapp.data.database.DataBase
import com.example.taskapp.data.database.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideTaskDao(dataBase: DataBase): TaskDao {
        return dataBase.taskDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext applicationContext: Context): DataBase {
        return Room.databaseBuilder(applicationContext, DataBase::class.java, "DataBase").build()
    }

}