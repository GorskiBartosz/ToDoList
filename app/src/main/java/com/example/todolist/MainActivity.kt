/**
 * Project: com.example.todolist
 * Name: MainActivity.kt
 * Purpose:
 *
 * @author Bartosz Gorski
 * @date 2019-06-02
 */
package com.example.todolist

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var tasks:ArrayList<Task>
    private lateinit var adapter: ToDoRecyclerViewAdapter
    private lateinit var database:AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        addTaskButton.setOnClickListener{
            startActivityForResult(Intent(this, AddTask::class.java), 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==0 && resultCode==1 && data!=null)
        {
            val h = data.extras.get("hour") as Int
            val mi = data.extras.get("minute") as Int
            val d = data.extras.get("day") as Int
            val mo = data.extras.get("month") as Int
            val y = data.extras.get("year") as Int
            val n = data.extras.get("desc") as String
            val i = data.extras.get("imgId") as Int
            val task = Task(h,mi,d,mo,y,n,i)
            tasks.add(task)
            AsyncTask.execute {
                database.taskDAO().insertAll(task)
                tasks = database.taskDAO().getAll() as ArrayList<Task>
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun init(){
        tasks=ArrayList()
        AsyncTask.execute {

            try {
                database = AppDataBase.getInstance(this)

                database.taskDAO().getAll().forEach{
                    Log.d("Dupa", "${it.minute}")
                }
                tasks.addAll( database.taskDAO().getAll())
            } catch (e: Exception) {
                Log.i("MainActivityaa", e.message)

            }

        }
        adapter = ToDoRecyclerViewAdapter(tasks, this)
        toDoList.layoutManager = LinearLayoutManager(this)
        toDoList.adapter = adapter
    }


}
