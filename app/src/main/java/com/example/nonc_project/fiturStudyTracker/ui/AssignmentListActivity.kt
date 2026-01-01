package com.example.nonc_project.fiturStudyTracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityAssignmentListBinding
import com.example.nonc_project.fiturStudyTracker.viewmodel.AssignmentViewModel

class AssignmentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssignmentListBinding
    private val viewModel: AssignmentViewModel by viewModels()
    private lateinit var adapter: AssignmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courseId = intent.getStringExtra("COURSE_ID") ?: return

        adapter = AssignmentAdapter(emptyList())
        binding.rvAssignments.layoutManager = LinearLayoutManager(this)
        binding.rvAssignments.adapter = adapter

        viewModel.assignmentList.observe(this) { list ->
            adapter.updateData(list)
        }

        viewModel.loadAssignments(courseId)
    }
}
