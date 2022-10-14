package com.devsaul.bookfinderapp.domain.usecases

import com.devsaul.bookfinderapp.data.Repository
import com.devsaul.bookfinderapp.domain.models.Book
import java.util.*
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun searchBook(title: String? = null, author: String? = null): List<Book> {
        lateinit var books: List<Book>
        if (!title.isNullOrEmpty()) {
            books = repository.getBookForTitle(convertDataForResquest(title))
        } else if (!author.isNullOrEmpty()) {
            books = repository.getBookForAuthor(convertDataForResquest(author))
        }
        return books
    }

    private fun convertDataForResquest(param: String): String {
        return param.lowercase().replace(" ", "+")
    }

}


