package com.ulbra.storebooks.data.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ulbra.storebooks.R
import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.databinding.ItemStorebookBinding


class StoreBookViewHolder(
    private val binding: ItemStorebookBinding,
    private val onDelete: (StoreBook) -> Unit,
    private val onDetails: (StoreBook) -> Unit,
    private val onSelect: (StoreBook) -> Unit,
    private val onShowSelectMessage: () -> Unit,
    private val onShowDeleteMessage: () -> Unit,
    private val onEdit: (StoreBook) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: StoreBook) {
        binding.storebook = book

        binding.root.setBackgroundResource(
            if (book.isSelected) R.drawable.bg_item_selected
            else R.drawable.bg_item_default
        )

        binding.root.setOnClickListener {
            if (book.isSelected) {
                onDetails(book)
            } else {
                onSelect(book)
            }
        }

        binding.root.setOnLongClickListener {
            if (book.isSelected) {
                onDelete(book)
            } else {
                onShowDeleteMessage()
            }
            true
        }

        binding.btnDetails.setOnClickListener { onDetails(book) }
        binding.btnEdit.setOnClickListener { onEdit(book) }

        binding.btnDelete.setOnClickListener {
                onDelete(book)
        }

        binding.executePendingBindings()
    }
}
