package com.example.taskapp.domain

import com.example.taskapp.data.database.TaskRepository
import com.example.taskapp.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        repository.update(taskModel)

    }
}