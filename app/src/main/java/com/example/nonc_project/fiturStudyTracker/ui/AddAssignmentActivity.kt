package com.example.nonc_project.fiturStudyTracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAddAssignmentBinding

class AddAssignmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAssignmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAssignmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener { finish() }
        binding.btnSave.setOnClickListener {
            // nanti logic save
            finish()
        }
    }
}
