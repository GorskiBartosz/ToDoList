package com.example.todolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_task.*
import java.util.*

class AddTask : AppCompatActivity() {

    private var hours=0
    private var minutes=0
    private var days =0
    private var months=0
    private var years=0
    private var taskName= ""
    private var imgId = R.drawable.abc_ic_star_half_black_16dp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        chooseTimeButton.setOnClickListener{
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)
            val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{ _, h, m ->

                hours = h
                minutes = m
                var out:String = if(hours<10)
                    "0$hours"
                else
                    "$hours"
                out+= if(minutes<10)
                    ":0$minutes"
                else
                    ":$minutes"
                timeText.setText(out)
            }, hour, minute, true )
            tpd.show()
        }

        chooseDateButton.setOnClickListener{
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, y, m, d ->
                days = d
                months = m+1
                years = y
                var out:String = if(day<10)
                    "0$days"
                else
                    "$days"
                out += if(months<10)
                    "-0$months"
                else
                    "-$months"
                out+="-$years"

                dateText.setText(out)
            }, year, month,day )
            dpd.show()
        }

        submitButton.setOnClickListener{
            taskName = descEditText.text.toString()
            val intent = Intent()
            intent.putExtra("hour", hours)
            intent.putExtra("minute", minutes)
            intent.putExtra("day", days)
            intent.putExtra("month", months)
            intent.putExtra("year", years)
            intent.putExtra("desc", taskName)
            intent.putExtra("imgId", imgId)

            setResult(1, intent)
            finish()
        }

        cancelButton.setOnClickListener{
            setResult(2)
            finish()
        }


    }
}
