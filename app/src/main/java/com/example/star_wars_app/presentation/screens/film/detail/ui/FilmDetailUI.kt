package com.example.star_wars_app.presentation.screens.film.detail.ui

import com.example.star_wars_app.domain.model.Film
import com.example.star_wars_app.domain.model.Character
import com.example.star_wars_app.domain.model.Planet

data class FilmDetailUI(
    val film: Film? = null,
    val characters: List<Character> = emptyList(),
    val planets: List<Planet> = emptyList()
)