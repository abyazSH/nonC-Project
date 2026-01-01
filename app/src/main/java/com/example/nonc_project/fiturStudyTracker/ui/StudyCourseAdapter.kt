package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.databinding.ItemStudyCourseBinding
import com.example.nonc_project.fiturStudyTracker.model.StudyCourse

class StudyCourseAdapter(private var courses: List<StudyCourse>)
    : RecyclerView.Adapter<StudyCourseAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemStudyCourseBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStudyCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses[position]

        holder.binding.tvCourseName.text = course.courseName
        holder.binding.tvTotal.text = "${course.totalAssignment} tugas"
        holder.binding.tvOverdue.text = "Overdue: ${course.overdueCount}"

        holder.itemView.setOnClickListener {
            val i = Intent(it.context, AssignmentListActivity::class.java)
            i.putExtra("COURSE_ID", course.courseId)
            it.context.startActivity(i)
        }
    }

    override fun getItemCount() = courses.size

    fun updateData(newData: List<StudyCourse>) {
        courses = newData
        notifyDataSetChanged()
    }
}
