package com.example.taskapp.domain

import com.example.taskapp.data.database.TaskRepository
import com.example.taskapp.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        repository.delete(taskModel)

    }
}