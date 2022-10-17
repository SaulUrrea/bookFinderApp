package com.devsaul.bookfinderapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devsaul.bookfinderapp.data.room.entities.BookEntity

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table ORDER BY id DESC")
    suspend fun getAllBooks(): List<BookEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inset(quote: BookEntity)

    @Query("DELETE FROM book_table WHERE id = :id")
    suspend fun deleteById(id: Int?)
}