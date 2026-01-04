package com.example.nonc_project.fiturStudyTracker.repository

import com.example.nonc_project.fiturStudyTracker.model.StudyCourse
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID


class StudyTrackerRepository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun courseRef() =
        db.collection("courses")

    fun getCourses(onResult: (List<StudyCourse>) -> Unit) {
        val userId = auth.uid ?: return

        courseRef()
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.documents.mapNotNull {
                    it.toObject<StudyCourse>()
                }
                onResult(list)
            }
    }

    fun addCourse(
        name: String,
        lecturer: String,
        onResult: () -> Unit
    ) {
        val userId = auth.uid ?: return
        val courseId = UUID.randomUUID().toString()

        val course = StudyCourse(
            courseId = courseId,
            userId = userId,
            name = name,
            lecturer = lecturer
        )

        courseRef()
            .document(courseId)
            .set(course)
            .addOnSuccessListener { onResult() }
    }

    fun deleteCourse(courseId: String, onResult: () -> Unit) {
        courseRef()
            .document(courseId)
            .delete()
            .addOnSuccessListener { onResult() }
    }
}
