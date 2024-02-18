package com.vsu.lab4

// TaskController.kt

class TaskController(private val repository: TaskRepository, private val view: TaskView) {
    fun addTask(task: Task) {
        repository.addTask(task)
    }

    fun getAllTasks() {
        val tasks = repository.getAllTasks()
        view.displayTasks(tasks)
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
    }

    fun deleteTask(taskId: Int) {
        repository.deleteTask(taskId)
    }
}
