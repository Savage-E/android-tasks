package com.vsu.lab4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
//import kotlinx.android.synthetic.main.item_todo.view.viewColorTag
import java.util.Random

class TodoAdapter(val tasks: List<Task>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // 3 functions of the view holder
    // 1st func
    // In this Layout inflatter is called which converts view in such a form that adapter can consume it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_task, parent, false)
        )
    }

    override fun getItemCount() = tasks.size

    // 2nd func
    // this will set data in each card
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(tasks[position]) // we are passing the object of the list that we made in the ToDoModel.kt
    }

    // 3rd func
    override fun getItemId(position: Int): Long {
        return tasks[position].id
    }

    // view holder is present inside the recycler view
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            with(itemView) {
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[Random().nextInt(colors.size)]
                val viewColorTag: View = itemView.findViewById(R.id.viewColorTag)
                val txtShowTitle: TextView = itemView.findViewById(R.id.txtShowTitle)
                val txtShowTask: TextView = itemView.findViewById(R.id.txtShowTask)
                val txtShowStatus: TextView = itemView.findViewById(R.id.txtShowStatus)

                viewColorTag.setBackgroundColor(randomColor)
                txtShowTitle.text = task.title
                txtShowTask.text = task.description
                txtShowStatus.text = if (task.isCompleted) "In progress" else "Complete"
                updateTime(task.deadline.time)
                updateDate(task.deadline.date)
            }
        }

        private fun updateTime(time: Long) {
            //Mon, 5 Jan 2020
            val txtShowTime: TextView = itemView.findViewById(R.id.txtShowTime)
            val myformat = "h:mm a"
            val sdf = SimpleDateFormat(myformat)
            txtShowTime.text = sdf.format(Date(time))

        }

        private fun updateDate(time: Long) {
            val txtShowDate: TextView = itemView.findViewById(R.id.txtShowDate)
            //Mon, 5 Jan 2020
            val myformat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            txtShowDate.text = sdf.format(Date(time))

        }
    }

}