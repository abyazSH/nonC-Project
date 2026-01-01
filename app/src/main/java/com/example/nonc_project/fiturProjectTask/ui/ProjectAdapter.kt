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
        val tvTaskBadge: TextView = itemView.findViewById(R.id.tvTaskBadge)
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

        // Progress
        holder.progressBar.progress = project.progressPercentage
        holder.tvProgressPercent.text =
            "${project.progressPercentage}% selesai"

        // ===== BADGE TASK AKTIF =====
        val activeCount = project.activeTaskCount

        if (activeCount > 0) {
            holder.tvTaskBadge.visibility = View.VISIBLE
            holder.tvTaskBadge.text = activeCount.toString()

            val badgeBg = when {
                activeCount <= 3 -> R.drawable.badge_green
                activeCount <= 6 -> R.drawable.badge_orange
                else -> R.drawable.badge_red
            }

            holder.tvTaskBadge.setBackgroundResource(badgeBg)
        } else {
            holder.tvTaskBadge.visibility = View.GONE
        }

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
