package com.example.nonc_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(
    private val items: List<CardItem>,
    private val onItemClick: (CardItem, Int) -> Unit  // Lambda untuk click dengan position
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageItem: ImageView = itemView.findViewById(R.id.image_item)
        private val titleItem: TextView = itemView.findViewById(R.id.title_item)
        private val descItem: TextView = itemView.findViewById(R.id.desc_item)

        fun bind(item: CardItem, onItemClick: (CardItem, Int) -> Unit) {
            imageItem.setImageResource(item.imageRes)
            titleItem.text = item.title
            descItem.text = item.description

            // Set click listener pada seluruh item
            itemView.setOnClickListener {
                onItemClick(item, adapterPosition)
            }
        }
    }
}
