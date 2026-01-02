package com.example.nonc_project.fiturStudyTracker.repository

import com.example.nonc_project.fiturStudyTracker.model.StudyCourse
import java.util.UUID

class StudyTrackerRepository {

    private val courseList = mutableListOf<StudyCourse>()

    fun getCourses(onResult: (List<StudyCourse>) -> Unit) {
        onResult(courseList)
    }

    fun addCourse(
        userId: String,
        name: String,
        lecturer: String,
        onResult: () -> Unit
    ) {
        courseList.add(
            StudyCourse(
                courseId = UUID.randomUUID().toString(),
                userId = userId,
                name = name,
                lecturer = lecturer
            )
        )
        onResult()
    }
}
