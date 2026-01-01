package com.example.nonc_project.fiturProjectTask.repository

import com.example.nonc_project.fiturProjectTask.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import android.util.Log


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
        projectId: String,
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
            .addOnSuccessListener {
                Log.d("TASK_REPO", "Task updated: $taskId")

                updateActiveTaskCount(projectId)
                onResult(true)
            }
            .addOnFailureListener {
                Log.e("TASK_REPO", "Task update FAILED", it)
                onResult(false)
            }

    }


    fun calculateProjectProgress(
        projectId: String,
        onResult: (Int) -> Unit
    ) {
        taskRef
            .whereEqualTo("projectId", projectId)
            .get()
//            .addOnSuccessListener { snapshot ->
//                val tasks = snapshot.toObjects<Task>()
//                if (tasks.isEmpty()) {
//                    onResult(0)
//                    return@addOnSuccessListener
//                }
//                onResult(tasks.sumOf { it.progress } / tasks.size)
//            }
            .addOnSuccessListener { snapshot ->
                val tasks = snapshot.toObjects<Task>()
                val avg = if (tasks.isEmpty()) 0 else tasks.sumOf { it.progress } / tasks.size

                Log.d("TASK_REPO", "Calculated project avg: $avg")
                onResult(avg)
            }

    }
    fun updateActiveTaskCount(projectId: String) {
        taskRef
            .whereEqualTo("projectId", projectId)
            .whereIn("status", listOf("TODO", "IN PROGRESS"))
            .get()
            .addOnSuccessListener { snapshot ->
                val count = snapshot.size()

                FirebaseFirestore.getInstance()
                    .collection("projects")
                    .document(projectId)
                    .update("activeTaskCount", count)
            }
    }

}
