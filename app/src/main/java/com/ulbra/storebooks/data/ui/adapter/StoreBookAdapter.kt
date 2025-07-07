package com.ulbra.storebooks.data.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.databinding.ItemStorebookBinding

class StoreBookAdapter(
    private val onDelete: (StoreBook) -> Unit,
    private val onDetails: (StoreBook) -> Unit,
    private val onSelect: (StoreBook) -> Unit,
    private val onShowSelectMessage: () -> Unit,
    private val onShowDeleteMessage: () -> Unit,
    private val onEdit: (StoreBook) -> Unit,
) : ListAdapter<StoreBook, StoreBookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreBookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStorebookBinding.inflate(inflater, parent, false)
        return StoreBookViewHolder(
            binding,
            onDelete,
            onDetails,
            onSelect,
            onShowSelectMessage,
            onShowDeleteMessage,
            onEdit
        )
    }

    override fun onBindViewHolder(holder: StoreBookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<StoreBook>() {
            override fun areItemsTheSame(oldItem: StoreBook, newItem: StoreBook): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StoreBook, newItem: StoreBook): Boolean {
                return oldItem == newItem
            }
        }
    }
}