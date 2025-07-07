package com.ulbra.storebooks.data.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ulbra.storebooks.data.StoreBook

class StoreBookDiffCallback : DiffUtil.ItemCallback<StoreBook>() {
    override fun areItemsTheSame(oldItem: StoreBook, newItem: StoreBook): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StoreBook, newItem: StoreBook): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.author == newItem.author &&
                oldItem.description == newItem.description &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.status == newItem.status &&
                oldItem.isSelected == newItem.isSelected &&
                oldItem.favorite == newItem.favorite
    }
}