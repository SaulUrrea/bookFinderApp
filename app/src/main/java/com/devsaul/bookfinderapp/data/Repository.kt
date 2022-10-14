package com.devsaul.bookfinderapp.data

import android.util.Log
import com.devsaul.bookfinderapp.data.network.NetworkService
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.domain.models.toDomain
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkService: NetworkService
) {
    suspend fun getBookForAuthor(author: String): List<Book> {
        val resp = networkService.getBookForAuthor(author)
        return resp.map { it.toDomain() }
    }
    suspend fun getBookForTitle(title: String): List<Book> {
        val resp = networkService.getBookForTitle(title)
        return resp.map { it.toDomain() }
    }
}