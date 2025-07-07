package com.ulbra.storebooks.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class StoreBookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val yearPublication: Int,
    val description: String,
    val imageUrl: String?,
    val status: String,
    val favorite: Boolean = false,
    val isSelected: Boolean = false
)
