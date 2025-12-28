package com.example.nonc_project.fiturProjectTask.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.R
import com.example.nonc_project.fiturProjectTask.model.Project
import android.content.Context

class ProjectAdapter(
    private var projectList: List<Project>,
    private val context: Context
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
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

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val i = Intent(context, TaskListActivity::class.java)
            i.putExtra("PROJECT_ID", project.projectId)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int = projectList.size

    fun updateData(newList: List<Project>) {
        projectList = newList
        notifyDataSetChanged()
    }
}
