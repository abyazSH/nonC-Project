package com.example.nonc_project.fiturProjectTask.model

data class Task(
    var taskId: String = "",
    var projectId: String = "",
    var title: String = "",
    var description: String = "",
    var progress: Int = 0,
    var status: String = "TODO",
    var createdAt: Long = System.currentTimeMillis()
)


