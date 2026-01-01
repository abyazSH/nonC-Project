package com.example.nonc_project.fiturStudyTracker.model

data class StudyCourse(
    val courseId: String = "",
    val userId: String = "",
    val name: String = "",
    val lecturer: String = "",
    val colorTag: String = "#FF9800",
    val createdAt: Long = System.currentTimeMillis()
)
