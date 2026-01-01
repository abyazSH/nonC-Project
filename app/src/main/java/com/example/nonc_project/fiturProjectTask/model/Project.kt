package com.example.nonc_project.fiturProjectTask.model

data class Project(
    var projectId: String = "",
    var userId: String = "",

    var title: String = "",
    var description: String = "",

    var startDate: Long = 0L,
    var dueDate: Long = 0L,

    var priority: String = "Medium",
    var category: String = "",

    var progressPercentage: Int = 0,
    var status: String = "Not Started",
    var activeTaskCount: Int = 0,

    var createdAt: Long = System.currentTimeMillis()
)
