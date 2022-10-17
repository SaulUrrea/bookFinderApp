package com.devsaul.bookfinderapp.domain.models

import com.devsaul.bookfinderapp.data.room.entities.BookEntity


data class ApiResponse(
    val docs: List<Book>?,
    val numFound: Int?,
    val numFoundExact: Boolean?,
    val num_found: Int?,
    val offset: Any?,
    val q: String?,
    val start: Int?
)

data class Book(
    val id: Int? = null,
    val author_name: List<String>?,
    val contributor: List<String>?,
    val first_publish_year: Int?,
    val language: List<String>?,
    val number_of_pages_median: Int?,
    val person: List<String>?,
    val place: List<String>?,
    val publish_place: List<String>?,
    val publisher: List<String>?,
    val subject: List<String>?,
    val time_facet: List<String>?,
    val title: String?,
    val subtitle: String?,
    val isbn: List<String>?,
    val _version_: Long?
)

fun BookEntity.toDomain() = Book(
    id,
    author_name?.split(","),
    contributor?.split(","),
    first_publish_year,
    language?.split(","),
    number_of_pages_median,
    person?.split(","),
    place?.split(","),
    publish_place?.split(","),
    publisher?.split(","),
    subject?.split(","),
    time_facet?.split(","),
    title,
    subtitle,
    isbn?.split(","),
    _version_
)