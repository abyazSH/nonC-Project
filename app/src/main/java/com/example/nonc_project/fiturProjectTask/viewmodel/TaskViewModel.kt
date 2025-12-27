package com.example.nonc_project.fiturProjectTask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturProjectTask.model.Task
import com.example.nonc_project.fiturProjectTask.repository.TaskRepository
import com.example.nonc_project.fiturProjectTask.repository.ProjectRepository

class TaskViewModel : ViewModel() {

    private val taskRepo = TaskRepository()
    private val projectRepo = ProjectRepository()

    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> = _taskList

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    /**
     * Load tasks by project
     */
    fun loadTasks(projectId: String) {
        taskRepo.getTasksByProject(projectId) {
            _taskList.postValue(it)
        }
    }

    /**
     * Create task
     */
    fun createTask(task: Task) {
        taskRepo.createTask(task) {
            _isSuccess.postValue(it)
        }
    }

    /**
     * Update task + auto update project progress
     */
    fun updateTaskAndProject(
        projectId: String,
        taskId: String,
        progress: Int,
        status: String
    ) {
        taskRepo.updateTaskProgress(taskId, progress, status) { success ->
            if (success) {
                taskRepo.calculateProjectProgress(projectId) { avg ->
                    projectRepo.updateProjectProgress(projectId, avg)
                }
                _isSuccess.postValue(true)
            } else {
                _isSuccess.postValue(false)
            }
        }
    }
}
