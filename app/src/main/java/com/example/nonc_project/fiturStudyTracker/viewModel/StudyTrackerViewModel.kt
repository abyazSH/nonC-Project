package com.example.nonc_project.fiturStudyTracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nonc_project.fiturStudyTracker.model.StudyCourse
import com.example.nonc_project.fiturStudyTracker.repository.AssignmentRepository
import com.example.nonc_project.fiturStudyTracker.repository.StudyTrackerRepository

class StudyTrackerViewModel : ViewModel() {

    private val courseRepo = StudyTrackerRepository()
    private val assignmentRepo = AssignmentRepository()

    // ===== COURSE LIST =====
    private val _courseList = MutableLiveData<List<StudyCourse>>()
    val courseList: LiveData<List<StudyCourse>> = _courseList

    // ===== ASSIGNMENT STAT =====
    private val _assignmentStats = MutableLiveData<Triple<Int, Int, Int>>()
    val assignmentStats: LiveData<Triple<Int, Int, Int>> = _assignmentStats

    // ===== LOAD COURSES =====
    fun loadCourses() {
        courseRepo.getCourses { courses ->
            _courseList.postValue(courses)
        }
    }

    // ===== ADD COURSE =====
    fun addCourse(name: String, lecturer: String) {
        courseRepo.addCourse(name, lecturer) {
            loadCourses()
        }
    }

    // ===== DELETE COURSE =====
    fun deleteCourse(courseId: String) {
        assignmentRepo.deleteAssignmentsByCourse(courseId)
        courseRepo.deleteCourse(courseId) {
            loadCourses()
        }
    }

    // ===== LOAD ASSIGNMENT STAT (FIXED) =====
    fun loadAssignmentStats(courseId: String) {
        assignmentRepo.getAssignmentStats(courseId) { stats ->
            _assignmentStats.postValue(stats)
        }
    }
}
