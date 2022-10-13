package com.devsaul.bookfinderapp.data.network

import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiClient {
    @GET("/.json")
    suspend fun getAllQuotes(): Response<List<Any>>
}