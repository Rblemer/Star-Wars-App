package com.example.star_wars_app.domain.model

data class PagedList<T>(
    val next: String?,
    val previous: String?,
    val results: List<T>
)