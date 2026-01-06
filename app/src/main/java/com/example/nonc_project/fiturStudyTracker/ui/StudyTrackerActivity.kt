package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.HomePage
import com.example.nonc_project.R
import com.example.nonc_project.databinding.ActivityStudyTrackerBinding
import com.example.nonc_project.fiturProjectTask.ui.ProjectListActivity
import com.example.nonc_project.fiturStudyTracker.viewmodel.StudyTrackerViewModel
import com.example.nonc_project.profile

class StudyTrackerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudyTrackerBinding
    private val viewModel: StudyTrackerViewModel by viewModels()
    private lateinit var adapter: StudyCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudyTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupBottomNavigation()

        viewModel.courseList.observe(this) { list ->
            adapter.updateData(list)

            binding.layoutEmpty.visibility =
                if (list.isEmpty()) View.VISIBLE else View.GONE

            binding.rvCourses.visibility =
                if (list.isEmpty()) View.GONE else View.VISIBLE
        }

        binding.fabAddCourse.setOnClickListener {
            startActivity(Intent(this, AddCourseActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCourses()
    }

    private fun setupRecycler() {
        adapter = StudyCourseAdapter(emptyList())
        binding.rvCourses.layoutManager = LinearLayoutManager(this)
        binding.rvCourses.adapter = adapter
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.menu_study

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomePage::class.java))
                    true
                }
                R.id.menu_project -> {
                    startActivity(Intent(this, ProjectListActivity::class.java))
                    true
                }
                R.id.menu_study -> true
                R.id.menu_profile -> {
                    startActivity(Intent(this, profile::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
