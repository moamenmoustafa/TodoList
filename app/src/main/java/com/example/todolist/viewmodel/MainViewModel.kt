package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDataBase
import kotlinx.coroutines.launch

class MainViewModel (val app : Application) : AndroidViewModel(app){
    private val Doa = TodoDataBase.getDataBase(app).todoDao()
    val todos : LiveData<List<Todo>> = Doa.getAllTodo()

    fun insertTodo(title : String) = viewModelScope.launch {
        Doa.insert(Todo(title = title))
    }
    fun updateTodo(todo: Todo) = viewModelScope.launch {
        Doa.update(todo.copy(isDone = !todo.isDone))
    }
    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        Doa.delete(todo)
    }
}