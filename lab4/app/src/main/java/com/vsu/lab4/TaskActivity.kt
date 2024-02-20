package com.vsu.lab4

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

class TaskActivity : AppCompatActivity(), View.OnClickListener {
    private val taskRepository = TaskRepository.getInstance()
    private var task: Task? = null
    private lateinit var myCalendar: Calendar
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener
    private var finalDate = 0L
    private var finalTime = 0L

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        task = intent.getParcelableExtra("task")
        setupViews()
    }

    private fun setupViews() {
        val dateEdt = findViewById<TextInputEditText>(R.id.dateEdt)
        val timeEdt = findViewById<TextInputEditText>(R.id.timeEdt)
        val saveBtn = findViewById<MaterialButton>(R.id.saveBtn)
        val cancelBtn = findViewById<MaterialButton>(R.id.cancelBtn)

        task?.let {
            findViewById<TextInputLayout>(R.id.titleInpLay).editText?.setText(it.title)
            findViewById<TextInputLayout>(R.id.taskInpLay).editText?.setText(it.description)
            val dateFormat = SimpleDateFormat("EEE, d MMM yyyy")
            val timeFormat = SimpleDateFormat("h:mm a")
            finalDate = it.deadline.date
            finalTime = it.deadline.time
            dateEdt.setText(dateFormat.format(Date(finalDate)))
            timeEdt.setText(timeFormat.format(Date(finalTime)))
            findViewById<TextInputLayout>(R.id.timeInptLay).visibility = View.VISIBLE
        }

        dateEdt.setOnClickListener(this)
        timeEdt.setOnClickListener(this)
        saveBtn.setOnClickListener(this)
        cancelBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dateEdt -> setListener()
            R.id.timeEdt -> setTimeListener()
            R.id.saveBtn -> saveTodo()
            R.id.cancelBtn -> cancelTodo()
        }
    }

    private fun saveTodo() {
        val titleInpLay = findViewById<TextInputLayout>(R.id.titleInpLay)
        val taskInpLay = findViewById<TextInputLayout>(R.id.taskInpLay)
        val title = titleInpLay.editText?.text.toString()
        val description = taskInpLay.editText?.text.toString()
        val newTask =
            Task(Random.nextLong(), title, description, false, DateTime(finalDate, finalTime))
        if (task == null) {
            taskRepository.addTask(newTask)
        } else {
            newTask.id = task!!.id
            taskRepository.updateTask(newTask)
        }
        finish()
    }

    private fun cancelTodo() {
        finish()
    }

    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()
        timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, min ->
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            myCalendar.set(Calendar.MINUTE, min)
            updateTime()
        }
        val timePickerDialog = TimePickerDialog(
            this,
            timeSetListener,
            myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    private fun updateTime() {
        val sdf = SimpleDateFormat("h:mm a")
        finalTime = myCalendar.time.time
        findViewById<TextInputEditText>(R.id.timeEdt).setText(sdf.format(myCalendar.time))
    }

    private fun setListener() {
        myCalendar = Calendar.getInstance()
        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate()
        }
        val datePickerDialog = DatePickerDialog(
            this,
            dateSetListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun updateDate() {
        val sdf = SimpleDateFormat("EEE, d MMM yyyy")
        finalDate = myCalendar.time.time
        findViewById<TextInputEditText>(R.id.dateEdt).setText(sdf.format(myCalendar.time))
        findViewById<TextInputLayout>(R.id.timeInptLay).visibility = View.VISIBLE
    }
}