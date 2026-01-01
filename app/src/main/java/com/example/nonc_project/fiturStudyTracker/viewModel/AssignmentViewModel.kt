package com.example.nonc_project.fiturStudyTracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment
import com.example.nonc_project.fiturStudyTracker.repository.AssignmentRepository

class AssignmentViewModel : ViewModel() {

    private val repo = AssignmentRepository()

    private val _assignmentList = MutableLiveData<List<StudyAssignment>>()
    val assignmentList: LiveData<List<StudyAssignment>> = _assignmentList

    fun loadAssignments(courseId: String) {
        repo.getAssignmentsByCourse(courseId) {
            _assignmentList.postValue(it)
        }
    }
}
