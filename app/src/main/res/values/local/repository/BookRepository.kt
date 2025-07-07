package values.local.repository

import androidx.lifecycle.LiveData
import com.ulbra.storebooks.data.StoreBook
import values.local.dao.StoreBookDao

class BookRepository(private val bookDao: values.local.dao.StoreBookDao) {

    val allBooks: LiveData<List<StoreBook>> = bookDao.getAllBooks()

    suspend fun insert(book: StoreBook) {
        bookDao.insert(book)
    }

    suspend fun update(book: StoreBook) {
        bookDao.update(book)
    }

    suspend fun delete(book: StoreBook) {
        bookDao.delete(book)
    }
}