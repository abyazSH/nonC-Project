package com.example.nonc_project.fiturStudyTracker.repository

import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.UUID

class AssignmentRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun assignmentRef() = db.collection("assignments")
    private fun uid() = auth.currentUser?.uid ?: ""

    // ==============================
    // LOAD ASSIGNMENTS PER COURSE
    // ==============================
    fun loadAssignments(
        courseId: String,
        onResult: (List<StudyAssignment>) -> Unit
    ) {
        val userId = uid()
        if (userId.isEmpty()) return

        assignmentRef()
            .whereEqualTo("courseId", courseId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                onResult(snap.toObjects(StudyAssignment::class.java))
            }
    }

    // ==============================
    // ADD ASSIGNMENT
    // ==============================
    fun addAssignment(
        courseId: String,
        title: String,
        desc: String,
        onResult: () -> Unit
    ) {
        val userId = uid()
        if (userId.isEmpty()) return

        val assignmentId = UUID.randomUUID().toString()

        val assignment = StudyAssignment(
            assignmentId = assignmentId,
            courseId = courseId,
            userId = userId,
            title = title,
            description = desc,
            dueDate = System.currentTimeMillis()
        )

        assignmentRef()
            .document(assignmentId)
            .set(assignment)
            .addOnSuccessListener { onResult() }
    }

    // ==============================
    // MARK DONE (SAFE)
    // ==============================
    fun markDone(
        assignmentId: String,
        onResult: () -> Unit
    ) {
        val userId = uid()
        if (userId.isEmpty()) return

        assignmentRef()
            .document(assignmentId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists() && doc.getString("userId") == userId) {
                    doc.reference
                        .update("status", "DONE")
                        .addOnSuccessListener { onResult() }
                }
            }
    }

    // ==============================
    // DELETE ASSIGNMENTS BY COURSE (SAFE)
    // ==============================
    fun deleteAssignmentsByCourse(courseId: String) {
        val userId = uid()
        if (userId.isEmpty()) return

        assignmentRef()
            .whereEqualTo("courseId", courseId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                snap.documents.forEach { it.reference.delete() }
            }
    }

    // ==============================
    // STATISTIK
    // ==============================
    fun getAssignmentStats(
        courseId: String,
        onResult: (Triple<Int, Int, Int>) -> Unit
    ) {
        val userId = uid()
        if (userId.isEmpty()) return

        assignmentRef()
            .whereEqualTo("courseId", courseId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.toObjects(StudyAssignment::class.java)
                onResult(
                    Triple(
                        list.size,
                        list.count { it.status == "TODO" },
                        list.count { it.status == "DONE" }
                    )
                )
            }
    }
}
