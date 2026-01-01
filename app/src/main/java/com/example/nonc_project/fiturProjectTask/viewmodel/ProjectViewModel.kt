package com.example.nonc_project.fiturProjectTask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturProjectTask.model.Project
import com.example.nonc_project.fiturProjectTask.repository.ProjectRepository

class ProjectViewModel : ViewModel() {

    private val repo = ProjectRepository()

    private val _projectList = MutableLiveData<List<Project>>()
    val projectList: LiveData<List<Project>> = _projectList

    // 🔥 GANTI loadProjects → observeProjects
    fun observeProjects(userId: String) {
        repo.observeProjectsByUser(userId) {
            _projectList.postValue(it)
        }
    }

    fun createProject(project: Project) {
        repo.createProject(project) {}
    }
}
