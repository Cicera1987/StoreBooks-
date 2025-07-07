package com.ulbra.storebooks.data.local.repository

import androidx.lifecycle.LiveData
import com.ulbra.storebooks.data.local.dao.StoreBookDao
import com.ulbra.storebooks.data.local.entities.StoreBookEntity

class BookRepository(private val storeBookDao: StoreBookDao) {

    val allBooks: LiveData<List<StoreBookEntity>> = storeBookDao.getAllBooks()

    suspend fun insert(book: StoreBookEntity) {
        storeBookDao.insert(book)
    }

    suspend fun update(book: StoreBookEntity) {
        storeBookDao.update(book)
    }

    suspend fun delete(book: StoreBookEntity) {
        storeBookDao.delete(book)
    }
}