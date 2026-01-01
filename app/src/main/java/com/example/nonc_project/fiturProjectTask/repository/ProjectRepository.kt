package com.example.nonc_project.fiturProjectTask.repository

import com.example.nonc_project.fiturProjectTask.model.Project
import com.google.firebase.firestore.FirebaseFirestore

class ProjectRepository {

    private val projectRef = FirebaseFirestore.getInstance()
        .collection("projects")

    fun createProject(project: Project, onResult: (Boolean) -> Unit) {
        projectRef.document(project.projectId)
            .set(project)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    // 🔥 REALTIME OBSERVER (INI YANG PENTING)
    fun observeProjectsByUser(
        userId: String,
        onResult: (List<Project>) -> Unit
    ) {
        projectRef
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) {
                    onResult(emptyList())
                    return@addSnapshotListener
                }
                onResult(snapshot.toObjects(Project::class.java))
            }
    }

    fun updateProjectProgress(projectId: String, progress: Int) {
        val status = when {
            progress == 0 -> "Not Started"
            progress in 1..99 -> "In Progress"
            else -> "Completed"
        }

        projectRef.document(projectId)
            .update(
                mapOf(
                    "progressPercentage" to progress,
                    "status" to status
                )
            )
    }
}
