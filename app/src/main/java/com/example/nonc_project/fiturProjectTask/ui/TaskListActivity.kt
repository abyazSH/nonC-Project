package com.example.nonc_project.fiturProjectTask.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityTaskListBinding
import com.example.nonc_project.fiturProjectTask.model.Task
import com.example.nonc_project.fiturProjectTask.viewmodel.TaskViewModel

class TaskListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskListBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    private lateinit var projectId: String
    private var allTasks: List<Task> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectId = intent.getStringExtra("PROJECT_ID") ?: return

        // BACK
        binding.btnBack.setOnClickListener { finish() }

        // RV
        adapter = TaskAdapter(emptyList())
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = adapter

        // ===== SPINNER FILTER (WAJIB ADA) =====
        val statusList = listOf("ALL", "TODO", "IN PROGRESS", "DONE")
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            statusList
        )
        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        binding.spFilter.adapter = spinnerAdapter
        // =====================================

        // OBSERVE TASK
        viewModel.taskList.observe(this) { list ->
            allTasks = list
            adapter.updateData(list)
        }

        // FILTER LOGIC
        binding.spFilter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View?, pos: Int, id: Long
                ) {
                    val status = parent.getItemAtPosition(pos).toString()
                    val filtered =
                        if (status == "ALL") allTasks
                        else allTasks.filter { it.status == status }

                    adapter.updateData(filtered)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

        // FAB ADD TASK
        binding.fabAddTask.setOnClickListener {
            val i = Intent(this, AddTaskActivity::class.java)
            i.putExtra("PROJECT_ID", projectId)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTasks(projectId)
    }
}
