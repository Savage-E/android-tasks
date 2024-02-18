package com.vsu.lab4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var taskController: TaskController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        taskController = TaskController()

        val addButton: Button = findViewById(R.id.add_button)
        val titleEditText: EditText = findViewById(R.id.title_edit_text)
        val descriptionEditText: EditText = findViewById(R.id.description_edit_text)
        val statusEditText: EditText = findViewById(R.id.status_edit_text)
        val deadlineEditText: EditText = findViewById(R.id.deadline_edit_text)

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val status = statusEditText.text.toString().toBoolean()
            val deadline = deadlineEditText.text.toString()

            val task = Task(title, description, status, deadline)
            taskController.addTask(task)

            updateTasksView()
        }
    }

    private fun updateTasksView() {
        val tasksTextView: TextView = findViewById(R.id.tasks_text_view)
        val tasks = taskController.viewTasks()

        tasksTextView.text = tasks.joinToString("\n") { task ->
            "Title: ${task.title}, Description: ${task.description}, Status: ${if (task.status) "Completed" else "Not Completed"}, Deadline: ${task.deadline}"
        }
    }
}




