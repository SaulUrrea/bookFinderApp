package com.devsaul.bookfinderapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devsaul.bookfinderapp.data.room.dao.BookDao
import com.devsaul.bookfinderapp.data.room.entities.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBookDao(): BookDao
}

