package com.example.nonc_project.fiturStudyTracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAssignmentDetailBinding
import com.example.nonc_project.fiturStudyTracker.viewmodel.AssignmentViewModel
import java.util.Date

class AssignmentDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssignmentDetailBinding
    private val viewModel: AssignmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // =========================
        // REQUIRED DATA
        // =========================
        val assignmentId = intent.getStringExtra("ASSIGNMENT_ID") ?: return
        val courseId = intent.getStringExtra("COURSE_ID") ?: return

        // =========================
        // OPTIONAL DISPLAY DATA
        // =========================
        val title = intent.getStringExtra("TITLE").orEmpty()
        val desc = intent.getStringExtra("DESC").orEmpty()
        val status = intent.getStringExtra("STATUS").orEmpty()
        val due = intent.getLongExtra("DUE", 0L)

        // =========================
        // BIND UI
        // =========================
        binding.tvTitle.text = title
        binding.tvDesc.text = desc
        binding.tvStatus.text = status
        binding.tvDue.text = "Due: ${Date(due)}"

        // =========================
        // DONE ACTION
        // =========================
        binding.btnDone.setOnClickListener {
            viewModel.markAsDone(assignmentId, courseId)
            finish()
        }
    }
}
