package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityAssignmentListBinding
import com.example.nonc_project.fiturStudyTracker.viewmodel.AssignmentViewModel

class AssignmentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssignmentListBinding
    private val viewModel: AssignmentViewModel by viewModels()
    private lateinit var adapter: AssignmentAdapter

    private lateinit var courseId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        courseId = intent.getStringExtra("COURSE_ID") ?: return

        // ===== BACK =====
        binding.btnBack.setOnClickListener {
            finish()
        }

        // ===== RV =====
        adapter = AssignmentAdapter(emptyList())
        binding.rvAssignments.layoutManager = LinearLayoutManager(this)
        binding.rvAssignments.adapter = adapter

        // ===== OBSERVE =====
        viewModel.assignmentList.observe(this) { list ->
            adapter.updateData(list)

            binding.layoutEmpty.visibility =
                if (list.isEmpty()) View.VISIBLE else View.GONE

            binding.rvAssignments.visibility =
                if (list.isEmpty()) View.GONE else View.VISIBLE
        }

        // ===== LOAD =====
        viewModel.loadAssignments(courseId)

        // ===== ADD =====
        binding.fabAdd.setOnClickListener {
            val i = Intent(this, AddAssignmentActivity::class.java)
            i.putExtra("COURSE_ID", courseId)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAssignments(courseId)
    }
}
