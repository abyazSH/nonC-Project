package com.example.nonc_project.fiturProjectTask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturProjectTask.model.Project
import com.example.nonc_project.fiturProjectTask.repository.ProjectRepository

class ProjectViewModel : ViewModel() {

    private val repository = ProjectRepository()

    private val _projectList = MutableLiveData<List<Project>>()
    val projectList: LiveData<List<Project>> = _projectList

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    /**
     * Load projects by user
     */
    fun loadProjects(userId: String) {
        repository.getProjectsByUser(userId) {
            _projectList.postValue(it)
        }
    }

    /**
     * Create project
     */
    fun createProject(project: Project) {
        repository.createProject(project) {
            _isSuccess.postValue(it)
        }
    }

    /**
     * Delete project
     */
    fun deleteProject(projectId: String) {
        repository.deleteProject(projectId) {
            _isSuccess.postValue(it)
        }
    }
}
