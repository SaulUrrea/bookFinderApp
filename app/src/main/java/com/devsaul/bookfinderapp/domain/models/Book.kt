package com.devsaul.bookfinderapp.domain.models


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
)

fun Book.toDomain() = Book(author_name, contributor, first_publish_year, language, number_of_pages_median, person, place, publish_place, publisher, subject, time_facet, title)