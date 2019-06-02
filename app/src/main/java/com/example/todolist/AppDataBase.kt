/**
 * Project: com.example.todolist
 * Name: AppDataBase
 * Purpose:
 *
 * @author Bartosz Gorski
 * @date 2019-06-02
 */
package com.example.todolist

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database( entities = [(Task::class)], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDAO() : TaskDAO

    companion object {

        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java, "task.db")
                .build()
    }
}