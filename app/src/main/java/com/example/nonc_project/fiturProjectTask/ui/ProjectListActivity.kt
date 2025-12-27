package com.example.nonc_project.fiturProjectTask.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityProjectListBinding
import com.example.nonc_project.fiturProjectTask.viewmodel.ProjectViewModel
import com.google.firebase.auth.FirebaseAuth

class ProjectListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectListBinding
    private val viewModel: ProjectViewModel by viewModels()
    private lateinit var adapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProjectAdapter(emptyList())
        binding.rvProject.layoutManager = LinearLayoutManager(this)
        binding.rvProject.adapter = adapter

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        viewModel.projectList.observe(this) {
            adapter.updateData(it)
        }

        viewModel.loadProjects(userId)
    }
}
