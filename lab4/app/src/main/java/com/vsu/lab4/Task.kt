package com.vsu.lab4

// Task.kt
data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val deadline: DateTime
)

data class DateTime(
    val date: Long,
    val time: Long,
)

