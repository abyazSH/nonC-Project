package com.example.nonc_project.fiturProjectTask.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.databinding.ItemTaskBinding
import com.example.nonc_project.fiturProjectTask.model.Task

class TaskAdapter(
    private var tasks: List<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.binding.apply {
            tvTaskTitle.text = task.title ?: "-"
            tvTaskDesc.text = task.description ?: "-"
            taskProgress.progress = task.progress ?: 0
            tvTaskStatus.text = task.status ?: "TODO"
        }

        holder.itemView.setOnClickListener {
            val i = Intent(it.context, EditTaskActivity::class.java)
            i.putExtra("PROJECT_ID", task.projectId)
            i.putExtra("TASK_ID", task.taskId)
            it.context.startActivity(i)
        }

    }

    override fun getItemCount(): Int = tasks.size

    fun updateData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
