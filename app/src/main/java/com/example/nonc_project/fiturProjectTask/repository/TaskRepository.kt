package com.example.nonc_project.fiturProjectTask.repository

import com.example.nonc_project.fiturProjectTask.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import android.util.Log
import com.google.firebase.auth.FirebaseAuth


class TaskRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val taskRef = db.collection("tasks")

    private fun uid() = auth.currentUser?.uid ?: ""

    fun createTask(task: Task, onResult: (Boolean) -> Unit) {
        val userId = uid()
        if (userId.isEmpty()) return

        task.userId = userId

        taskRef.document(task.taskId)
            .set(task)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun getTasksByProject(
        projectId: String,
        onResult: (List<Task>) -> Unit
    ) {
        val userId = uid()
        if (userId.isEmpty()) return

        taskRef
            .whereEqualTo("projectId", projectId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                onResult(snap.toObjects(Task::class.java))
            }
            .addOnFailureListener { onResult(emptyList()) }
    }

    fun updateTaskProgress(
        projectId: String,
        taskId: String,
        progress: Int,
        status: String,
        onResult: (Boolean) -> Unit
    ) {
        val userId = uid()
        if (userId.isEmpty()) return

        taskRef.document(taskId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists() && doc.getString("userId") == userId) {
                    doc.reference.update(
                        mapOf(
                            "progress" to progress,
                            "status" to status,
                            "updatedAt" to System.currentTimeMillis()
                        )
                    ).addOnSuccessListener {
                        updateActiveTaskCount(projectId)
                        onResult(true)
                    }
                } else {
                    onResult(false)
                }
            }
    }

    fun calculateProjectProgress(
        projectId: String,
        onResult: (Int) -> Unit
    ) {
        val userId = uid()
        if (userId.isEmpty()) return

        taskRef
            .whereEqualTo("projectId", projectId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                val tasks = snap.toObjects(Task::class.java)
                val avg =
                    if (tasks.isEmpty()) 0
                    else tasks.sumOf { it.progress } / tasks.size
                onResult(avg)
            }
    }

    fun updateActiveTaskCount(projectId: String) {
        val userId = uid()
        if (userId.isEmpty()) return

        taskRef
            .whereEqualTo("projectId", projectId)
            .whereEqualTo("userId", userId)
            .whereIn("status", listOf("TODO", "IN PROGRESS"))
            .get()
            .addOnSuccessListener { snap ->
                db.collection("projects")
                    .document(projectId)
                    .update("activeTaskCount", snap.size())
            }
    }
}
