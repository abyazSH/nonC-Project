package com.example.nonc_project.fiturProjectTask.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityTaskListBinding
import com.example.nonc_project.fiturProjectTask.ui.TaskAdapter
import com.example.nonc_project.fiturProjectTask.viewmodel.TaskViewModel

class TaskListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskListBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    private lateinit var projectId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectId = intent.getStringExtra("PROJECT_ID") ?: return

        adapter = TaskAdapter(emptyList())
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = adapter

        viewModel.taskList.observe(this) {
            adapter.updateData(it)
        }

        binding.fabAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra("PROJECT_ID", projectId)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.loadTasks(projectId)
    }

}
