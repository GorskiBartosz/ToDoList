/**
 * Project: com.example.todolist
 * Name: Task.kt
 * Purpose:
 *
 * @author Bartosz Gorski
 * @date 2019-06-02
 */
package com.example.todolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "task")
class Task(
    @ColumnInfo(name = "hour") val hour:Int,
    @ColumnInfo(name = "minute") val minute:Int,
    @ColumnInfo(name = "day")val day:Int,
    @ColumnInfo(name = "month") val month:Int,
    @ColumnInfo(name = "year") val year:Int,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "imgID") val imgID: Int,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id : Long = 0
)