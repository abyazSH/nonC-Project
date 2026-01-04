package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityStudyTrackerBinding
import com.example.nonc_project.fiturStudyTracker.viewmodel.StudyTrackerViewModel

class StudyTrackerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudyTrackerBinding
    private val viewModel: StudyTrackerViewModel by viewModels()
    private lateinit var adapter: StudyCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = StudyCourseAdapter(emptyList())
        binding.rvCourses.layoutManager = LinearLayoutManager(this)
        binding.rvCourses.adapter = adapter

        viewModel.courseList.observe(this) { list ->
            adapter.updateData(list)

            binding.layoutEmpty.visibility =
                if (list.isEmpty()) View.VISIBLE else View.GONE

            binding.rvCourses.visibility =
                if (list.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.loadCourses()

        binding.fabAddCourse.setOnClickListener {
            startActivity(Intent(this, AddCourseActivity::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.loadCourses()
    }

}
