package com.ulbra.storebooks.data.mapper

import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.data.ReadingStatus
import com.ulbra.storebooks.data.local.entities.StoreBookEntity

fun StoreBook.toEntity(): StoreBookEntity {
    return StoreBookEntity(
        id = this.id,
        title = this.title,
        author = this.author,
        yearPublication = this.yearPublication,
        description = this.description,
        imageUrl = this.imageUrl,
        status = this.status.name,
        favorite = this.favorite,
        isSelected = this.isSelected
    )
}

fun StoreBookEntity.toModel(): StoreBook {
    return StoreBook(
        id = this.id,
        title = this.title,
        author = this.author,
        yearPublication = this.yearPublication,
        description = this.description,
        imageUrl = this.imageUrl ?: "",
        status = ReadingStatus.valueOf(this.status),
        favorite = this.favorite,
        isSelected = this.isSelected
    )
}
