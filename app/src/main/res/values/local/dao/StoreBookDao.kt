package values.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ulbra.storebooks.data.StoreBook

interface StoreBookDao {

    @Query("SELECT * FROM storebook_table ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<StoreBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: StoreBook)

    @Update
    suspend fun update(book: StoreBook)

    @Delete
    suspend fun delete(book: StoreBook)
}