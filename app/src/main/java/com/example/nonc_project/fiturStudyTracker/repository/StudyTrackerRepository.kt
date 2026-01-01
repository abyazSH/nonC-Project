package com.example.nonc_project.fiturStudyTracker.repository

import com.example.nonc_project.fiturStudyTracker.model.StudyCourse

class StudyTrackerRepository {

    fun getCourses(onResult: (List<StudyCourse>) -> Unit) {

        val dummy = listOf(
            StudyCourse(
                courseId = "C1",
                userId = "x5MrI3hEyPOJXD5JbqbBTjwHL6A2",
                name = "Mobile Programming",
                lecturer = "Pak Budi",
                colorTag = "#4CAF50"
            ),
            StudyCourse(
                courseId = "C2",
                userId = "x5MrI3hEyPOJXD5JbqbBTjwHL6A2",
                name = "Artificial Intelligence",
                lecturer = "Bu Sari",
                colorTag = "#2196F3"
            ),
            StudyCourse(
                courseId = "C3",
                userId = "x5MrI3hEyPOJXD5JbqbBTjwHL6A2",
                name = "Cloud Computing",
                lecturer = "Pak Dimas",
                colorTag = "#FF9800"
            )
        )

        onResult(dummy)
    }
}
