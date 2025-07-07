package com.ulbra.storebooks.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoreBook(
    val id: Int,
    val title: String,
    val author: String,
    val yearPublication: Int,
    val description: String,
    val imageUrl: String,
    var status: ReadingStatus = ReadingStatus.NAO_LIDO,
    var favorite: Boolean = false,
    var isSelected: Boolean = false
) : Parcelable

enum class ReadingStatus(val descricao: String) {
    NAO_LIDO("Não lido"),
    LENDO("Lendo"),
    LIDO("Lido")
}