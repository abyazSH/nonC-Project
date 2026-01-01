package com.example.nonc_project.fiturStudyTracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.databinding.ItemStudyAssignmentBinding
import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment

class StudyAssignmentAdapter(private var data: List<StudyAssignment>)
    : RecyclerView.Adapter<StudyAssignmentAdapter.VH>() {

    inner class VH(val binding: ItemStudyAssignmentBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemStudyAssignmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val a = data[position]
        holder.binding.tvTitle.text = a.title
        holder.binding.tvDesc.text = a.description
        holder.binding.tvStatus.text = a.status
    }

    override fun getItemCount() = data.size

    fun updateData(newData: List<StudyAssignment>) {
        data = newData
        notifyDataSetChanged()
    }
}
