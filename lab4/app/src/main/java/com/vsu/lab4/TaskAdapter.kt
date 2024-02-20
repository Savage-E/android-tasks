package com.vsu.lab4

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random

class TaskAdapter(val tasks: MutableList<Task>, private val listener: ItemActionListener) : RecyclerView.Adapter<TaskAdapter.TodoViewHolder>() {

    interface ItemActionListener {
        fun onEditAction(task: Task)
        fun onDeleteAction(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(tasks[position])
        setupPopupMenu(holder, position)
    }

    private fun setupPopupMenu(holder: TodoViewHolder, position: Int) {
        val menuIcon = holder.itemView.findViewById<ImageView>(R.id.item_menu)
        menuIcon.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, view)
            val inflater: MenuInflater = popupMenu.menuInflater
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.editItm -> {
                        listener.onEditAction(tasks[position])
                        true
                    }
                    R.id.deleteItm -> {
                        listener.onDeleteAction(tasks[position])
                        true
                    }
                    else -> false
                }
            }
            inflater.inflate(R.menu.context_menu, popupMenu.menu)
            popupMenu.show()
        }
    }

    fun getSelectedTask(position: Int): Task {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return tasks[position].id
    }

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
                txtShowStatus.text = if (task.isCompleted) "Complete" else "In progress"
                updateTime(task.deadline.time)
                updateDate(task.deadline.date)
            }
        }

        private fun updateTime(time: Long) {
            val txtShowTime: TextView = itemView.findViewById(R.id.txtShowTime)
            val myformat = "h:mm a"
            val sdf = SimpleDateFormat(myformat)
            txtShowTime.text = sdf.format(Date(time))
        }

        private fun updateDate(time: Long) {
            val txtShowDate: TextView = itemView.findViewById(R.id.txtShowDate)
            val myformat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            txtShowDate.text = sdf.format(Date(time))
        }
    }
}
