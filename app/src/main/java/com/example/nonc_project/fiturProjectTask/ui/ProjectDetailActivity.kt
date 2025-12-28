package com.example.nonc_project.fiturProjectTask.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nonc_project.databinding.ActivityProjectDetailBinding
import com.example.nonc_project.fiturProjectTask.viewmodel.ProjectViewModel
import com.example.nonc_project.fiturProjectTask.viewmodel.TaskViewModel
import com.google.firebase.auth.FirebaseAuth

class ProjectDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectDetailBinding
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var projectId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectId = intent.getStringExtra("PROJECT_ID")!!

        projectViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val userId = FirebaseAuth.getInstance().uid!!

        // Load awal
        projectViewModel.loadProjects(userId)
        taskViewModel.loadTasks(projectId)

        // Observe project progress
        projectViewModel.projectList.observe(this) { list ->
            val project = list.firstOrNull { it.projectId == projectId } ?: return@observe

            binding.tvProjectTitle.text = project.title
            binding.progressBarProject.progress = project.progressPercentage
            binding.tvProgressPercent.text = "${project.progressPercentage}%"
        }

        binding.btnOpenTasks.setOnClickListener {
            startActivity(
                Intent(this, TaskListActivity::class.java)
                    .putExtra("PROJECT_ID", projectId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        projectViewModel.loadProjects(FirebaseAuth.getInstance().uid!!)
    }
}
