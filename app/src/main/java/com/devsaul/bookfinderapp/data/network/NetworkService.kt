package com.devsaul.bookfinderapp.data.network

import android.util.Log
import com.devsaul.bookfinderapp.domain.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val api: NetworkApiClient
) {

    suspend fun getBookForTitle(title: String): List<Book> {
        return withContext(Dispatchers.IO) {
            val response = api.getBooksForTitle(title)
            response.body()?.docs ?: emptyList()
        }
    }

    suspend fun getBookForAuthor(author: String): List<Book> {
        return withContext(Dispatchers.IO) {
            val response = api.getBooksForAuthor(author)
            response.body()?.docs  ?: emptyList()
        }
    }
}