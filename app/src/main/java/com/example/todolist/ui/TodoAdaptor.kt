package com.example.todolist.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdaptor(
    private val onToogleDone : (Todo) -> Unit ,
    private val onDelete : (Todo) -> Unit
) : ListAdapter<Todo , TodoAdaptor.TodoViewHolder>(DiffCallBack()) {

    class TodoViewHolder(var binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoAdaptor.TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context ) , parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoAdaptor.TodoViewHolder, position: Int) {
        val todo = getItem(position)
        with(holder.binding){
            textTodo.text = todo.title
            checkboxTodo.isChecked = todo.isDone
            textTodo.paintFlags = if(todo.isDone) {
                textTodo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }else {
                textTodo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            checkboxTodo.setOnCheckedChangeListener { _, _ -> onToogleDone(todo) }
            deleteBtn.setOnClickListener { onDelete(todo) }
            }
        }
    class DiffCallBack() : DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return  oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }


}


