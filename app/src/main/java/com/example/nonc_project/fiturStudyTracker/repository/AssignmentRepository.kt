package com.example.nonc_project.fiturStudyTracker.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment
import java.util.UUID

class AssignmentRepository {

    private val _assignmentList = MutableLiveData<List<StudyAssignment>>()
    val assignmentList: LiveData<List<StudyAssignment>> = _assignmentList

    private val data = mutableListOf<StudyAssignment>()

    fun loadAssignments(courseId: String) {
        // dummy awal
        data.clear()
        data.addAll(
            listOf(
                StudyAssignment(
                    assignmentId = UUID.randomUUID().toString(),
                    courseId = courseId,
                    title = "UI Wireframe",
                    description = "Design Study Tracker UX",
                    dueDate = System.currentTimeMillis() + 86400000,
                    status = "TODO"
                ),
                StudyAssignment(
                    assignmentId = UUID.randomUUID().toString(),
                    courseId = courseId,
                    title = "RecyclerView Task",
                    description = "Implement adapter",
                    dueDate = System.currentTimeMillis() - 3600000, // ❗ lewat 1 jam
                    status = "IN_PROGRESS"
                )
            )
        )

        // 🔴 TAMBAHAN DI SINI
        checkOverdue()

        _assignmentList.value = data.toList()
    }

    // 🔴 FUNGSI BARU
    private fun checkOverdue() {
        val now = System.currentTimeMillis()

        data.forEachIndexed { index, assignment ->
            if (
                assignment.status != "DONE" &&
                assignment.dueDate < now
            ) {
                data[index] = assignment.copy(status = "OVERDUE")
            }
        }
    }

    fun markDone(assignmentId: String) {
        val idx = data.indexOfFirst { it.assignmentId == assignmentId }
        if (idx != -1) {
            data[idx] = data[idx].copy(status = "DONE")
            _assignmentList.value = data.toList()
        }
    }

    fun addAssignment(courseId: String, title: String, desc: String) {
        data.add(
            StudyAssignment(
                assignmentId = UUID.randomUUID().toString(),
                courseId = courseId,
                title = title,
                description = desc,
                dueDate = System.currentTimeMillis(),
                status = "TODO"
            )
        )

        checkOverdue()
        _assignmentList.value = data.toList()
    }
}
