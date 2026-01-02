package com.example.nonc_project.fiturStudyTracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment
import com.example.nonc_project.fiturStudyTracker.repository.AssignmentRepository

class AssignmentViewModel : ViewModel() {

    private val repository = AssignmentRepository()

    val assignmentList: LiveData<List<StudyAssignment>> =
        repository.assignmentList

    fun loadAssignments(courseId: String) {
        repository.loadAssignments(courseId)
    }

    fun markAsDone(assignmentId: String) {
        repository.markDone(assignmentId)
    }

    fun addAssignment(courseId: String, title: String, desc: String) {
        repository.addAssignment(courseId, title, desc)
    }
}
