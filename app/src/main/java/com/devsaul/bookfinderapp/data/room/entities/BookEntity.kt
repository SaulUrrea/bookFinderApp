package com.devsaul.bookfinderapp.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devsaul.bookfinderapp.domain.models.Book


@Entity(tableName = "book_table")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "author_name") val author_name: String?,
    @ColumnInfo(name = "contributor") val contributor: String?,
    @ColumnInfo(name = "first_publish_year") val first_publish_year: Int?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "number_of_pages_median") val number_of_pages_median: Int?,
    @ColumnInfo(name = "person") val person: String?,
    @ColumnInfo(name = "place") val place: String?,
    @ColumnInfo(name = "publish_place") val publish_place: String?,
    @ColumnInfo(name = "publisher") val publisher: String?,
    @ColumnInfo(name = "subject") val subject: String?,
    @ColumnInfo(name = "time_facet") val time_facet: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "subtitle") val subtitle: String?,
    @ColumnInfo(name = "isbn") val isbn: String?,
    @ColumnInfo(name = "_version_") val _version_: Long?
)

fun Book.toDatabase() = BookEntity(
    id ?: 0,
    author_name?.joinToString(","),
    contributor?.joinToString(","),
    first_publish_year,
    language?.joinToString(","),
    number_of_pages_median,
    person?.joinToString(","),
    place?.joinToString(","),
    publish_place?.joinToString(","),
    publisher?.joinToString(","),
    subject?.joinToString(","),
    time_facet?.joinToString(","),
    title,
    subtitle,
    isbn?.joinToString(","),
    _version_
)
