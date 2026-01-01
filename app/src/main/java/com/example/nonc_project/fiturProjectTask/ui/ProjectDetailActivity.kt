package com.example.nonc_project.fiturProjectTask.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.nonc_project.databinding.ActivityProjectDetailBinding
import com.example.nonc_project.fiturProjectTask.viewmodel.ProjectViewModel
import com.example.nonc_project.fiturProjectTask.viewmodel.TaskViewModel
import com.google.firebase.auth.FirebaseAuth

class ProjectDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectDetailBinding
    private val projectViewModel: ProjectViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()

    private lateinit var projectId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectId = intent.getStringExtra("PROJECT_ID") ?: return
        val userId = FirebaseAuth.getInstance().uid ?: return

        // 🔥 REALTIME OBSERVE PROJECT
        projectViewModel.observeProjects(userId)

        projectViewModel.projectList.observe(this) { list ->
            val project = list.firstOrNull { it.projectId == projectId }
                ?: return@observe

            binding.tvProjectTitle.text = project.title
            binding.progressBarProject.progress = project.progressPercentage
            binding.tvProgressPercent.text =
                "${project.progressPercentage}% selesai"
        }

        // Load tasks (opsional kalau mau dipakai)
        taskViewModel.loadTasks(projectId)

        binding.btnOpenTasks.setOnClickListener {
            startActivity(
                Intent(this, TaskListActivity::class.java)
                    .putExtra("PROJECT_ID", projectId)
            )
        }
    }
}
