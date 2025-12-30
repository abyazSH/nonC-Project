package com.example.nonc_project.fiturProjectTask.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.R
import com.example.nonc_project.fiturProjectTask.model.Project

class ProjectAdapter(
    private var projectList: List<Project>,
    private val context: Context
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val progressBar: ProgressBar = itemView.findViewById(R.id.projectProgressBar)
        val tvProgressPercent: TextView = itemView.findViewById(R.id.tvProgressPercent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projectList[position]

        holder.tvTitle.text = project.title
        holder.tvDescription.text = project.description

        // === PROGRESS PROJECT ===
        val progress = project.progressPercentage.coerceIn(0, 100)

        holder.progressBar.progress = progress
        holder.tvProgressPercent.text = "$progress% selesai"

        holder.itemView.setOnClickListener {
            val intent = Intent(context, TaskListActivity::class.java)
            intent.putExtra("PROJECT_ID", project.projectId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = projectList.size

    fun updateData(newList: List<Project>) {
        projectList = newList
        notifyDataSetChanged()
    }
}
