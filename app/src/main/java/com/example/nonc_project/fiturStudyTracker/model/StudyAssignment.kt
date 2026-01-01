package com.example.nonc_project.fiturStudyTracker.model

data class StudyAssignment(
    val assignmentId: String = "",
    val courseId: String = "",
    val title: String = "",
    val description: String = "",
    val dueDate: Long = 0L,
    val status: String = "TODO",
    val reminderEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
