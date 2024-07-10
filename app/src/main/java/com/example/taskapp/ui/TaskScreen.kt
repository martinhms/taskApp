package com.example.taskapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun TaskScreen(taskViewModel: TaskViewModel) {

    val showDialog: Boolean by taskViewModel.showDialog.observeAsState(initial = false)
    Box(modifier = Modifier.fillMaxSize()) {
        AddTaskDialog(showDialog = showDialog, onDismiss = { taskViewModel.onDialogClose() }, onTaskAdded = {taskViewModel.onTaskCreated(it)})
        FloatButton(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ,taskViewModel)
    }

}

@Composable
fun FloatButton(padding: Modifier, taskViewModel: TaskViewModel) {
    FloatingActionButton(
        onClick = {taskViewModel.onDialogOpen()},
        modifier = padding
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}


@Composable
fun AddTaskDialog(showDialog: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var task by remember { mutableStateOf("") }
    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add task",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = task, onValueChange = { task = it }, singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { onTaskAdded(task) }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Add task")
                }
            }

        }
    }

}
