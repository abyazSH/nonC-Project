package com.example.nonc_project.fiturProjectTask.repository

import com.example.nonc_project.fiturProjectTask.model.Project
import com.google.firebase.firestore.FirebaseFirestore

class ProjectRepository {

    private val db = FirebaseFirestore.getInstance()
    private val projectRef = db.collection("projects")

    fun createProject(project: Project, onResult: (Boolean) -> Unit) {
        val docId = project.projectId ?: projectRef.document().id
        project.projectId = docId

        projectRef.document(docId)
            .set(project)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun getProjectsByUser(
        userId: String,
        onResult: (List<Project>) -> Unit
    ) {
        projectRef
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.toObjects(Project::class.java))
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun deleteProject(projectId: String, onResult: (Boolean) -> Unit) {
        projectRef.document(projectId)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun updateProjectProgress(
        projectId: String,
        progress: Int
    ) {
        val status = when {
            progress == 0 -> "Not Started"
            progress in 1..99 -> "In Progress"
            else -> "Completed"
        }

        FirebaseFirestore.getInstance()
            .collection("projects")
            .document(projectId)
            .update(
                mapOf(
                    "progressPercentage" to progress,
                    "status" to status
                )
            )
    }

}
