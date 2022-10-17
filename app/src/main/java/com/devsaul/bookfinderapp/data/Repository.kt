package com.devsaul.bookfinderapp.data

import com.devsaul.bookfinderapp.data.network.NetworkService
import com.devsaul.bookfinderapp.data.room.dao.BookDao
import com.devsaul.bookfinderapp.data.room.entities.BookEntity
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.domain.models.toDomain
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkService: NetworkService,
    private val bookDao: BookDao
) {
    suspend fun getBookForAuthor(author: String): List<Book> {
        return networkService.getBookForAuthor(author)
    }
    suspend fun getBookForTitle(title: String): List<Book> {
        return networkService.getBookForTitle(title)
    }
    suspend fun getAllBooksFromDatabase(): List<Book>? {
        val response = bookDao.getAllBooks()
        return response.map { it.toDomain() }
    }

    suspend fun insertBook(book: BookEntity) {
        bookDao.inset(book)
    }

    suspend fun clearBookForId(book: BookEntity) {
        bookDao.deleteById(book.id)
    }
}