package com.devsaul.bookfinderapp.core

import android.content.Context
import androidx.room.Room
import com.devsaul.bookfinderapp.data.room.AppDatabase
import com.devsaul.bookfinderapp.utils.RoomDatabase.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideBookDao(db: AppDatabase) = db.getBookDao()

}