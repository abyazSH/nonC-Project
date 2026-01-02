package com.example.nonc_project.fiturStudyTracker.ui

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.databinding.ItemAssignmentBinding
import com.example.nonc_project.fiturStudyTracker.model.StudyAssignment
import java.util.concurrent.TimeUnit

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

        // ===== STATUS UX =====
        when (a.status) {
            "TODO" -> {
                holder.binding.tvStatus.text = "TODO"
                holder.binding.tvStatus.setBackgroundColor(Color.parseColor("#FEF3C7"))
                holder.binding.vAccent.setBackgroundColor(Color.parseColor("#F59E0B"))
            }

            "IN_PROGRESS" -> {
                holder.binding.tvStatus.text = "IN PROGRESS"
                holder.binding.tvStatus.setBackgroundColor(Color.parseColor("#DBEAFE"))
                holder.binding.vAccent.setBackgroundColor(Color.parseColor("#3B82F6"))
            }

            "DONE" -> {
                holder.binding.tvStatus.text = "DONE"
                holder.binding.tvStatus.setBackgroundColor(Color.parseColor("#D1FAE5"))
                holder.binding.vAccent.setBackgroundColor(Color.parseColor("#22C55E"))
            }

            "OVERDUE" -> {
                holder.binding.tvStatus.text = "OVERDUE"
                holder.binding.tvStatus.setBackgroundColor(Color.parseColor("#FEE2E2"))
                holder.binding.vAccent.setBackgroundColor(Color.parseColor("#EF4444"))
            }
        }

        // ===== DUE DATE UX =====
        val now = System.currentTimeMillis()
        val diff = a.dueDate - now
        val daysLeft = TimeUnit.MILLISECONDS.toDays(diff)

        holder.binding.tvDue.text = when {
            diff < 0 -> "Overdue"
            daysLeft == 0L -> "Due today"
            else -> "$daysLeft days left"
        }

        holder.binding.tvDue.setTextColor(
            when {
                diff < 0 -> Color.parseColor("#EF4444")
                daysLeft == 0L -> Color.parseColor("#F59E0B")
                else -> Color.parseColor("#6B7280")
            }
        )

        // ===== NAVIGATION =====
        holder.binding.root.setOnClickListener {
            val ctx = holder.itemView.context
            val intent = Intent(ctx, AssignmentDetailActivity::class.java)
            intent.putExtra("TITLE", a.title)
            intent.putExtra("DESC", a.description)
            intent.putExtra("STATUS", a.status)
            intent.putExtra("DUE", a.dueDate)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newList: List<StudyAssignment>) {
        items = newList
        notifyDataSetChanged()
    }
}
