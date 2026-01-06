package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityCourseDetailBinding
import com.example.nonc_project.fiturStudyTracker.viewmodel.StudyTrackerViewModel

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailBinding
    private val viewModel: StudyTrackerViewModel by viewModels()

    private lateinit var courseId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        courseId = intent.getStringExtra("COURSE_ID") ?: run {
            finish()
            return
        }

        // =============================
        // BACK BUTTON
        // =============================
        binding.btnBack.setOnClickListener {
            finish()
        }

        // =============================
        // LOAD DATA
        // =============================
        viewModel.loadCourses()
        viewModel.loadAssignmentStats(courseId)

        // =============================
        // OBSERVE COURSE DETAIL
        // =============================
        viewModel.courseList.observe(this) { list ->
            val course = list.firstOrNull { it.courseId == courseId } ?: return@observe
            binding.tvCourseName.text = course.name
            binding.tvLecturer.text = course.lecturer
        }

        // =============================
        // OBSERVE ASSIGNMENT STATS
        // =============================
        viewModel.assignmentStats.observe(this) { stats ->
            binding.tvTotal.text = stats.first.toString()
            binding.tvTodo.text = stats.second.toString()
            binding.tvDone.text = stats.third.toString()
        }

        // =============================
        // VIEW ASSIGNMENTS
        // =============================
        binding.btnAssignments.setOnClickListener {
            val intent = Intent(this, AssignmentListActivity::class.java)
            intent.putExtra("COURSE_ID", courseId)
            startActivity(intent)
        }

        // =============================
        // DELETE COURSE
        // =============================
        binding.btnDelete.setOnClickListener {
            showDeleteConfirm()
        }
    }

    private fun showDeleteConfirm() {
        AlertDialog.Builder(this)
            .setTitle("Delete Course")
            .setMessage("Yakin ingin menghapus mata kuliah ini beserta seluruh assignment?")
            .setPositiveButton("Hapus") { _, _ ->
                viewModel.deleteCourse(courseId)
                finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
