package com.vsu.lab4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), TaskAdapter.ItemActionListener {

    val list = arrayListOf<Task>()
    var taskAdapter = TaskAdapter(list, this)
    val taskRepository = TaskRepository.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val todoRv = findViewById<RecyclerView>(R.id.todoRv)
        todoRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.taskAdapter
        }
        initSwipe()
    }

    override fun onEditAction(task: Task) {
        val intent = Intent(this, TaskActivity::class.java)
        intent.putExtra("task", task)
        startActivity(intent)
    }

    override fun onDeleteAction(task: Task) {
        taskRepository.deleteTask(task.id)
        list.remove(task)
        taskAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        val tasksFromRepo = taskRepository.getAllTasks()
        list.clear()
        list.addAll(tasksFromRepo)
        taskAdapter.notifyDataSetChanged()
    }

    private fun initSwipe() {
        val simpleItemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = taskAdapter.getSelectedTask(position)
                if (direction == ItemTouchHelper.LEFT) {
                    taskRepository.deleteTask(task.id)
                    list.remove(task)
                    taskAdapter.notifyDataSetChanged()
                } else if (direction == ItemTouchHelper.RIGHT) {
                    task.isCompleted = !task.isCompleted
                    taskRepository.updateTask(task)
                    taskAdapter.notifyDataSetChanged()
                }
            }
        }
        val todoRv = findViewById<RecyclerView>(R.id.todoRv)
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(todoRv)
    }

    fun openNewTask(view: View) {
        startActivity(Intent(this, TaskActivity::class.java))
    }
}
