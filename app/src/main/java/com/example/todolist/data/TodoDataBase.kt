package com.example.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

    companion object {
        @Volatile private var INSTANCE : TodoDataBase?= null
        fun getDataBase(context: Context) : TodoDataBase{
            return INSTANCE?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    TodoDataBase::class.java,
                    "todo_database"
                ).build().also {INSTANCE = it}
            }
        }
    }

}