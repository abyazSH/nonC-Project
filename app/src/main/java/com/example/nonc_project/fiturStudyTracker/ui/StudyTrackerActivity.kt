package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityAssignmentListBinding
import com.example.nonc_project.fiturStudyTracker.viewModel.AssignmentViewModel

class AssignmentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssignmentListBinding
    private val viewModel: AssignmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courseId = intent.getStringExtra("COURSE_ID") ?: return

        val adapter = StudyAssignmentAdapter(emptyList())
        binding.rvAssignments.layoutManager = LinearLayoutManager(this)
        binding.rvAssignments.adapter = adapter

        viewModel.assignmentList.observe(this) {
            adapter.updateData(it)
        }

        viewModel.loadAssignments(courseId)
    }
}
