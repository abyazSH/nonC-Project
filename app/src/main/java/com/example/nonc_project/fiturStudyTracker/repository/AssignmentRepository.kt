package com.example.nonc_project.fiturStudyTracker.repository

import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.UUID

class AssignmentRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun assignmentRef() =
        db.collection("assignments")

    // ==============================
    // LOAD ASSIGNMENTS PER COURSE
    // ==============================
    fun loadAssignments(
        courseId: String,
        onResult: (List<StudyAssignment>) -> Unit
    ) {
        val userId = auth.uid ?: return

        assignmentRef()
            .whereEqualTo("courseId", courseId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.documents.mapNotNull {
                    it.toObject<StudyAssignment>()
                }
                onResult(list)
            }
    }


    // ==============================
    // ADD ASSIGNMENT (FIXED)
    // ==============================
    fun addAssignment(
        courseId: String,
        title: String,
        desc: String,
        onResult: () -> Unit
    ) {
        val userId = auth.uid ?: return
        val assignmentId = UUID.randomUUID().toString()

        val assignment = StudyAssignment(
            assignmentId = assignmentId,
            courseId = courseId,
            userId = userId, // ✅ WAJIB
            title = title,
            description = desc,
            dueDate = System.currentTimeMillis(),
            status = "TODO",
            reminderEnabled = true,
            createdAt = System.currentTimeMillis()
        )

        assignmentRef()
            .document(assignmentId)
            .set(assignment)
            .addOnSuccessListener { onResult() }
    }

    // ==============================
    // MARK DONE
    // ==============================
    fun markDone(
        assignmentId: String,
        courseId: String,
        onResult: () -> Unit
    ) {
        assignmentRef()
            .document(assignmentId)
            .update("status", "DONE")
            .addOnSuccessListener { onResult() }
    }

    // ==============================
    // DELETE BY COURSE (CASCADE)
    // ==============================
    fun deleteAssignmentsByCourse(courseId: String) {
        assignmentRef()
            .whereEqualTo("courseId", courseId)
            .get()
            .addOnSuccessListener { snap ->
                snap.documents.forEach {
                    it.reference.delete()
                }
            }
    }

    // ==============================
    // STATISTIK
    // ==============================
    fun getAssignmentStats(
        courseId: String,
        onResult: (Triple<Int, Int, Int>) -> Unit
    ) {
        val userId = auth.uid ?: return

        assignmentRef()
            .whereEqualTo("courseId", courseId)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.documents.mapNotNull {
                    it.toObject<StudyAssignment>()
                }

                val total = list.size
                val todo = list.count { it.status == "TODO" }
                val done = list.count { it.status == "DONE" }

                onResult(Triple(total, todo, done))
            }
    }
}
