package com.example.nonc_project.fiturStudyTracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAddCourseBinding
import com.example.nonc_project.fiturStudyTracker.model.StudyCourse
import com.example.nonc_project.fiturStudyTracker.viewmodel.StudyTrackerViewModel

class AddCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCourseBinding
    private val viewModel: StudyTrackerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveCourse.setOnClickListener {
            val name = binding.etCourseName.text.toString().trim()
            val lecturer = binding.etLecturer.text.toString().trim()

            if (name.isBlank()) return@setOnClickListener

            viewModel.addCourse(name, lecturer)
            finish()
        }

    }
}
