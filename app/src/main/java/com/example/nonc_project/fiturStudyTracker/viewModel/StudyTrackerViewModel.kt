package com.example.nonc_project.fiturStudyTracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturStudyTracker.model.StudyCourse
import com.example.nonc_project.fiturStudyTracker.repository.StudyTrackerRepository

class StudyTrackerViewModel : ViewModel() {

    private val repo = StudyTrackerRepository()

    private val _courseList = MutableLiveData<List<StudyCourse>>()
    val courseList: LiveData<List<StudyCourse>> = _courseList

    fun loadCourses() {
        repo.getCourses { courses ->
            _courseList.postValue(courses)
        }
    }
}
