package com.example.todolist

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun getAll() : List<Task>

    @Insert
    fun insertAll(vararg task:Task)

    @Delete
    fun delete(vararg task:Task)

    @Query("DELETE FROM task")
    fun deleteAll()
}
