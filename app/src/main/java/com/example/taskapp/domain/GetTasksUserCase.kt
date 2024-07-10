package com.example.taskapp.domain

import com.example.taskapp.data.database.TaskRepository
import com.example.taskapp.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUserCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = repository.tasks
}