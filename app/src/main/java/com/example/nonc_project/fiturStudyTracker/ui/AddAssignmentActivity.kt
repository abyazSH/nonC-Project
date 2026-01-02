package com.example.nonc_project.fiturStudyTracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAddAssignmentBinding
import com.example.nonc_project.fiturStudyTracker.viewmodel.AssignmentViewModel

class AddAssignmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAssignmentBinding
    private val viewModel: AssignmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAssignmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courseId = intent.getStringExtra("COURSE_ID") ?: return

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val desc = binding.etDesc.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(this, "Title wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addAssignment(courseId, title, desc)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}
