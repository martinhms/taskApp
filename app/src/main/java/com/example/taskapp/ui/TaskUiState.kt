package com.example.taskapp.ui

import com.example.taskapp.ui.model.TaskModel

sealed interface TaskUiState {

    object Loading:TaskUiState
    data class Error(val throwable: Throwable): TaskUiState
    data class Success(val task: List<TaskModel>) : TaskUiState
}