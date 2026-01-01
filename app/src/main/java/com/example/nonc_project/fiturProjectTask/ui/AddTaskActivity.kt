package com.example.nonc_project.fiturProjectTask.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAddTaskBinding
import com.example.nonc_project.fiturProjectTask.model.Task
import com.example.nonc_project.fiturProjectTask.viewmodel.TaskViewModel
import java.util.UUID

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val projectId = intent.getStringExtra("PROJECT_ID") ?: return

        binding.btnSaveTask.setOnClickListener {
            val title = binding.etTaskTitle.text.toString()
            val desc = binding.etTaskDescription.text.toString()

            if (title.isBlank()) {
                Toast.makeText(this, "Judul wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val task = Task(
                taskId = UUID.randomUUID().toString(),
                projectId = projectId,
                title = title,
                description = desc,
                progress = 0,
                status = "TODO",
                createdAt = System.currentTimeMillis()
            )

            viewModel.createTask(task)
            finish()
        }

        // ✅ CANCEL BUTTON
        binding.btnCancelTask.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Batalkan?")
                .setMessage("Data yang belum disimpan akan hilang")
                .setPositiveButton("Ya") { _, _ -> finish() }
                .setNegativeButton("Tidak", null)
                .show()
        }

    }

}
