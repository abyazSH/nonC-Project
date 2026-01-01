package com.example.nonc_project.fiturStudyTracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.databinding.ItemAssignmentBinding
import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment

class AssignmentAdapter(private var items: List<StudyAssignment>) :
    RecyclerView.Adapter<AssignmentAdapter.VH>() {

    inner class VH(val binding: ItemAssignmentBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemAssignmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val a = items[position]
        holder.binding.tvTitle.text = a.title
        holder.binding.tvDesc.text = a.description
        holder.binding.tvStatus.text = a.status
    }

    override fun getItemCount() = items.size

    fun updateData(newList: List<StudyAssignment>) {
        items = newList
        notifyDataSetChanged()
    }
}
