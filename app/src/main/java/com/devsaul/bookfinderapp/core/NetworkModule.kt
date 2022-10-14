package com.devsaul.bookfinderapp.core

import com.devsaul.bookfinderapp.data.network.NetworkApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://openlibrary.org/"

    @Singleton
    @Provides
    fun getBookForTitle(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): NetworkApiClient {
        return retrofit.create(NetworkApiClient::class.java)
    }
}