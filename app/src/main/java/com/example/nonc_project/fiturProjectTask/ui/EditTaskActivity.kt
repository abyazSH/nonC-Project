package com.example.nonc_project.fiturProjectTask.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityEditTaskBinding
import com.example.nonc_project.fiturProjectTask.viewmodel.TaskViewModel
import android.util.Log


class EditTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    private lateinit var projectId: String
    private lateinit var taskId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectId = intent.getStringExtra("PROJECT_ID")!!
        taskId = intent.getStringExtra("TASK_ID")!!

        val statusAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("TODO", "IN PROGRESS", "DONE")
        )
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spStatus.adapter = statusAdapter

        binding.seekProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvPercent.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.btnSave.setOnClickListener {
            Log.d("EDIT_TASK", "Save clicked. Progress=${binding.seekProgress.progress}")
            viewModel.updateTaskAndProject(
                projectId = projectId,
                taskId = taskId,
                progress = binding.seekProgress.progress,
                status = binding.spStatus.selectedItem.toString()
            )
            finish()
        }
    }
}
