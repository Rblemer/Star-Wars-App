package com.example.star_wars_app.presentation.screens.film.list.ui

import com.example.star_wars_app.domain.model.Film

data class FilmsUI(
    val films: List<Film> = emptyList(),
    val nextPage: String? = null
)