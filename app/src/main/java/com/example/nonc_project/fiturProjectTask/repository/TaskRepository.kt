package com.example.nonc_project.fiturProjectTask.repository

import com.example.nonc_project.fiturProjectTask.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

class TaskRepository {

    private val taskRef = FirebaseFirestore.getInstance().collection("tasks")

    fun createTask(task: Task, onResult: (Boolean) -> Unit) {
        taskRef.document(task.taskId)
            .set(task)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun getTasksByProject(
        projectId: String,
        onResult: (List<Task>) -> Unit
    ) {
        taskRef
            .whereEqualTo("projectId", projectId)
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.toObjects())
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun updateTaskProgress(
        taskId: String,
        progress: Int,
        status: String,
        onResult: (Boolean) -> Unit
    ) {
        taskRef.document(taskId)
            .update(
                mapOf(
                    "progress" to progress,
                    "status" to status,
                    "updatedAt" to System.currentTimeMillis()
                )
            )
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun calculateProjectProgress(
        projectId: String,
        onResult: (Int) -> Unit
    ) {
        taskRef
            .whereEqualTo("projectId", projectId)
            .get()
            .addOnSuccessListener { snapshot ->
                val tasks = snapshot.toObjects<Task>()
                if (tasks.isEmpty()) {
                    onResult(0)
                    return@addOnSuccessListener
                }
                onResult(tasks.sumOf { it.progress } / tasks.size)
            }
    }
}
