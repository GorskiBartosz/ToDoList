/**
 * Project: com.example.todolist
 * Name: ToDoRecyclerViewAdapter.kt
 * Purpose:
 *
 * @author Bartosz Gorski
 * @date 2019-06-02
 */
package com.example.todolist

import android.content.Context
import android.os.AsyncTask
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.task_layout.view.*

class ToDoRecyclerViewAdapter(private val tasks:ArrayList<Task>, private val context: Context) : RecyclerView.Adapter<ToDoRecyclerViewAdapter.ToDoRecyclerViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ToDoRecyclerViewHolder {
        val h = LayoutInflater.from(p0.context).inflate(R.layout.task_layout, p0, false) as ConstraintLayout
        return ToDoRecyclerViewHolder(h)
    }


    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(p0: ToDoRecyclerViewHolder, p1: Int) {
        p0.itemView.setOnClickListener{
            val database = AppDataBase.getInstance(context)
            if(tasks.size>0)
            {

                AsyncTask.execute { database.taskDAO().delete(tasks[p1])
                    tasks.removeAt(p1)
                }

            }
            notifyDataSetChanged()
        }

        val task = tasks[p1]
        p0.itemView.taskContent.text = task.content
        val date = "${task.day}.${task.month}.${task.year}"
        p0.itemView.taskDate.text = date
        val time = "${task.hour}:${task.minute}"
        p0.itemView.taskTime.text = time
        p0.itemView.taskImg.setImageResource(task.imgID)

    }

    class ToDoRecyclerViewHolder(private val task: ConstraintLayout) : RecyclerView.ViewHolder(task)
}