package com.vsu.lab4

class TaskRepository {
    private val tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun getAllTasks(): List<Task> {
        return tasks
    }

    fun updateTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
        }
    }

    fun deleteTask(taskId: Long) {
        tasks.removeIf { it.id == taskId }
    }
}