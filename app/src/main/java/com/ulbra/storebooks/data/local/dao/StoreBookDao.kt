package com.ulbra.storebooks.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ulbra.storebooks.data.local.entities.StoreBookEntity

@Dao
interface StoreBookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<StoreBookEntity>>

    @Insert
    suspend fun insert(book: StoreBookEntity)

    @Update
    suspend fun update(book: StoreBookEntity)

    @Delete
    suspend fun delete(book: StoreBookEntity)

    @Query("DELETE FROM books WHERE id = :bookId")
    suspend fun deleteById(bookId: Int)

}
