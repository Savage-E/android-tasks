package com.vsu.lab4

class TaskView {
    fun displayTasks(taskList: List<Task>) {
        for (task in taskList) {
            println("Task ${task.id}: ${task.title} (${if (task.isCompleted) "Completed" else "Not Completed"})")
        }
    }
}