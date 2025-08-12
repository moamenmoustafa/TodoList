package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.ui.TodoAdaptor
import com.example.todolist.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
     private lateinit var viewmodel : MainViewModel
     private lateinit var adaptor: TodoAdaptor
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         viewmodel = ViewModelProvider(this )[MainViewModel::class.java]
         adaptor = TodoAdaptor(
             onToogleDone = {viewmodel.updateTodo(it)},
             onDelete = {viewmodel.deleteTodo(it)}
         )
         binding.rtvTodo.adapter = adaptor
         binding.rtvTodo.layoutManager = LinearLayoutManager(this)

         binding.todoBtn.setOnClickListener {
             val text =binding.Todo.editText?.text.toString()
             if (text.isNotBlank()){
                 viewmodel.insertTodo(text)
                 binding.Todo.editText?.text?.clear()
             }
         }

         viewmodel.todos.observe(this){
             adaptor.submitList(it)
         }

    }
}