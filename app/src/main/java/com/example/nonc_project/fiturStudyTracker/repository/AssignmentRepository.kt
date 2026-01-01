package com.example.nonc_project.fiturStudyTracker.repository

import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment

class AssignmentRepository {

    fun getAssignmentsByCourse(courseId: String, onResult: (List<StudyAssignment>) -> Unit) {

        val dummy = listOf(
            StudyAssignment("A1", courseId, "Quiz 1", "Intro Quiz", System.currentTimeMillis(), "TODO"),
            StudyAssignment("A2", courseId, "UAS Project", "Final App", System.currentTimeMillis(), "IN_PROGRESS"),
            StudyAssignment("A3", courseId, "Homework", "Essay", System.currentTimeMillis(), "OVERDUE")
        )

        onResult(dummy)
    }
}
