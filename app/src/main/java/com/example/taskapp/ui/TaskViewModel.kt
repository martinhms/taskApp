package com.example.taskapp.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.AddTaskUseCase
import com.example.taskapp.domain.DeleteTaskUseCase
import com.example.taskapp.domain.GetTasksUserCase
import com.example.taskapp.domain.UpdateTaskUseCase
import com.example.taskapp.ui.TaskUiState.Success
import com.example.taskapp.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUserCase: GetTasksUserCase
) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = getTasksUserCase().map(::Success)
        .catch { TaskUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUiState.Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        //_tasks.add(TaskModel(task = task))
        _showDialog.value = false

        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onDialogOpen() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(task: TaskModel) {
        //Actualizar check
   //     val index = _tasks.indexOf(task)
     //   _tasks[index] = _tasks[index].let {
       //     it.copy(selected = !it.selected)
      //  }
            viewModelScope.launch(Dispatchers.IO) {
                updateTaskUseCase(task.copy(selected = !task.selected))
            }
    }

    fun onItemRemove(task: TaskModel) {
    //borrar item
    //    val taskToRemove = _tasks.find { it.id == task.id }
      //  _tasks.remove(taskToRemove)
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase(task)
        }
    }

}