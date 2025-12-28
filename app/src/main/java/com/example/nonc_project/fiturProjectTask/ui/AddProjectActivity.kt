package com.example.nonc_project.fiturProjectTask.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nonc_project.R
import com.example.nonc_project.databinding.ActivityAddProjectBinding
import com.example.nonc_project.fiturProjectTask.viewmodel.ProjectViewModel
import androidx.activity.viewModels
import com.example.nonc_project.fiturProjectTask.model.Project
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID


class AddProjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProjectBinding
    private val viewModel: ProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveProject.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDesc.text.toString()

            if (title.isBlank()) return@setOnClickListener

            val project = Project(
                projectId = UUID.randomUUID().toString(),
                userId = FirebaseAuth.getInstance().uid!!,
                title = title,
                description = desc,
                startDate = System.currentTimeMillis(),
                dueDate = System.currentTimeMillis(),
                priority = "Medium",
                category = "General",
                progressPercentage = 0,
                status = "Not Started"
            )


            viewModel.createProject(project)
            finish()
        }
    }
}
