package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.databinding.ItemCourseBinding
import com.example.nonc_project.fiturStudyTracker.model.StudyCourse

class StudyCourseAdapter(
    private var courses: List<StudyCourse>
) : RecyclerView.Adapter<StudyCourseAdapter.CourseVH>() {

    inner class CourseVH(val binding: ItemCourseBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVH {
        return CourseVH(
            ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        val course = courses[position]
        holder.binding.tvCourseName.text = course.name
        holder.binding.tvLecturer.text = course.lecturer

        holder.itemView.setOnClickListener {
            val i = Intent(it.context, AssignmentListActivity::class.java)
            i.putExtra("COURSE_ID", course.courseId)
            it.context.startActivity(i)
        }
    }

    override fun getItemCount() = courses.size

    fun updateData(newList: List<StudyCourse>) {
        courses = newList
        notifyDataSetChanged()
    }
}
