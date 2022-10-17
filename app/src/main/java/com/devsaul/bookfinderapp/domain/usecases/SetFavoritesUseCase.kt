package com.devsaul.bookfinderapp.domain.usecases

import com.devsaul.bookfinderapp.data.Repository
import com.devsaul.bookfinderapp.data.room.entities.toDatabase
import com.devsaul.bookfinderapp.domain.models.Book
import javax.inject.Inject

class SetFavoritesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun addFavoriteBook(book: Book) {
        repository.insertBook(book.toDatabase())
    }
    suspend fun removeFavoriteBook(book: Book) {
        repository.clearBookForId(book.toDatabase())
    }
    suspend fun getFavoritesBooks(): List<Book>? {
        return repository.getAllBooksFromDatabase()
    }
}