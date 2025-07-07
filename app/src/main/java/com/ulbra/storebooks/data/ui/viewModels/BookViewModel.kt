package com.ulbra.storebooks.data.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.ulbra.storebooks.data.local.database.StoreBookDatabase
import kotlinx.coroutines.launch
import androidx.lifecycle.map
import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.data.mapper.toEntity
import com.ulbra.storebooks.data.mapper.toModel


class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookDao = StoreBookDatabase.getDatabase(application).bookDao()
    private val _books = bookDao.getAllBooks().map { list -> list.map { it.toModel() } }

    val filteredBooks = MediatorLiveData<List<StoreBook>>().apply {
        addSource(_books) { value = it }
    }

    fun filterBooks(query: String) {
        val currentList = _books.value ?: return
        filteredBooks.value = if (query.isBlank()) {
            currentList
        } else {
            currentList.filter { it.title.contains(query, ignoreCase = true) }
        }
    }
    val books: LiveData<List<StoreBook>> =
        bookDao.getAllBooks().map { list -> list.map { it.toModel() } }

    fun addBook(book: StoreBook) {
        viewModelScope.launch {
            bookDao.insert(book.toEntity())
        }
    }

    fun updateBook(book: StoreBook) {
        viewModelScope.launch {
            bookDao.update(book.toEntity())
        }
    }

    fun deleteBook(book: StoreBook) {
        viewModelScope.launch {
            bookDao.delete(book.toEntity())
        }
    }
    fun deleteBookById(bookId: Int) {
        viewModelScope.launch {
            bookDao.deleteById(bookId)
        }
    }

}