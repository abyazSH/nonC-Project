package com.example.nonc_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(
    private val items: List<itemRiwayat>,
    private val onItemClick: (itemRiwayat) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_riwayat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tanggalItem: TextView = itemView.findViewById(R.id.tanggal_item)
        private val statusItem: TextView = itemView.findViewById(R.id.status_item)

        fun bind(item: itemRiwayat, onItemClick: (itemRiwayat) -> Unit) {
            tanggalItem.text = item.tanggal
            statusItem.text = item.status

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}
