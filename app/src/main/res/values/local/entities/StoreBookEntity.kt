package values.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "storebook_table")
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
) : Parcelable