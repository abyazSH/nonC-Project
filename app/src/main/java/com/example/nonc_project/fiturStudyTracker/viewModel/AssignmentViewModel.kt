package com.example.nonc_project.fiturStudyTracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment
import com.example.nonc_project.fiturStudyTracker.repository.AssignmentRepository

class AssignmentViewModel : ViewModel() {

    private val repository = AssignmentRepository()

    private val _assignmentList = MutableLiveData<List<StudyAssignment>>()
    val assignmentList: LiveData<List<StudyAssignment>> = _assignmentList

    // =========================
    // LOAD PER COURSE
    // =========================
    fun loadAssignments(courseId: String) {
        repository.loadAssignments(courseId) { list ->
            _assignmentList.postValue(list)
        }
    }

    // =========================
    // ADD
    // =========================
    fun addAssignment(courseId: String, title: String, desc: String) {
        repository.addAssignment(courseId, title, desc) {
            loadAssignments(courseId)
        }
    }

    // =========================
    // MARK DONE
    // =========================
    fun markAsDone(assignmentId: String, courseId: String) {
        repository.markDone(assignmentId, courseId) {
            loadAssignments(courseId)
        }
    }
}
