package com.example.nonc_project.fiturProjectTask.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAddTaskBinding
import com.example.nonc_project.fiturProjectTask.model.Task
import com.example.nonc_project.fiturProjectTask.repository.TaskRepository
import java.util.UUID

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val repository = TaskRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val projectId = intent.getStringExtra("PROJECT_ID") ?: return

        binding.btnSaveTask.setOnClickListener {
            saveTask(projectId)
        }
    }

    private fun saveTask(projectId: String) {
        val title = binding.etTaskTitle.text.toString()
        val desc = binding.etTaskDescription.text.toString()

        if (title.isBlank()) {
            Toast.makeText(this, "Title wajib diisi", Toast.LENGTH_SHORT).show()
            return
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

        repository.createTask(task) { success ->
            if (success) {
                Toast.makeText(this, "Task berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Gagal menyimpan task", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
