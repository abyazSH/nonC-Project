package com.example.nonc_project.fiturProjectTask.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.HomePage
import com.example.nonc_project.databinding.ActivityProjectListBinding
import com.example.nonc_project.fiturProjectTask.viewmodel.ProjectViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.nonc_project.R
import com.example.nonc_project.profile

class ProjectListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectListBinding
    private val viewModel: ProjectViewModel by viewModels()
    private lateinit var adapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupBottomNavigation()
        observeData()

        binding.fabAddProject.setOnClickListener {
            startActivity(Intent(this, AddProjectActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(emptyList(), this)
        binding.rvProject.layoutManager = LinearLayoutManager(this)
        binding.rvProject.adapter = adapter
    }

    private fun observeData() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        viewModel.projectList.observe(this) { projects ->
            adapter.updateData(projects)

            // tampilkan greeting hanya jika belum ada project
            binding.cardOverview.visibility =
                if (projects.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.loadProjects(userId)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.menu_project

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomePage::class.java))
                    true
                }

                R.id.menu_project -> true

                R.id.menu_profile -> {
                    startActivity(Intent(this, profile::class.java))
                    true
                }

                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().uid?.let {
            viewModel.loadProjects(it)
        }
    }
}
