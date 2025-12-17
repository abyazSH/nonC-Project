package com.example.nonc_project.fiturMl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonc_project.databinding.ItemRiwayatBinding
import java.text.SimpleDateFormat
import java.util.*

class RiwayatAdapter(
    private val items: List<RiwayatModel>,
    private val onClick: (RiwayatModel) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRiwayatBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRiwayatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tanggalItem.text =
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                .format(Date(item.timestamp))

        holder.binding.statusItem.text = item.result

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
