package com.vsu.lab4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task (
    val id: Long,
    var title: String,
    var description: String,
    var isCompleted: Boolean,
    var deadline: DateTime
) : Parcelable

@Parcelize
data class DateTime(
    val date: Long,
    val time: Long,
): Parcelable

