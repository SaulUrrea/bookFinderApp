package com.devsaul.bookfinderapp.data.network

import com.devsaul.bookfinderapp.domain.models.ApiResponse
import com.devsaul.bookfinderapp.domain.models.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiClient {

    @GET("search.json")
    suspend fun getBooksForTitle(@Query("title") title: String): Response<ApiResponse>

    @GET("search.json")
    suspend fun getBooksForAuthor(@Query("author") author: String): Response<ApiResponse>
}