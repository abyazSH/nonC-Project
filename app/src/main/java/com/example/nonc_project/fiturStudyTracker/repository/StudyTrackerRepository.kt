package com.example.nonc_project.fiturStudyTracker.repository

import com.example.nonc_project.fiturStudyTracker.model.StudyCourse

class StudyTrackerRepository {

    fun getCourses(onResult: (List<StudyCourse>) -> Unit) {

        // SIMULASI SEMENTARA (nanti ganti Classroom API)
        val dummy = listOf(
            StudyCourse("C1", "Mobile Programming", 4, 1),
            StudyCourse("C2", "Artificial Intelligence", 3, 0),
            StudyCourse("C3", "Cloud Computing", 2, 1)
        )

        onResult(dummy)
    }
}
