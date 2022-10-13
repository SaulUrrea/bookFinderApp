package com.devsaul.bookfinderapp.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_table")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "autor") val autor: String? = null,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean? = null,
    @ColumnInfo(name = "description") val description: String?=null
)
