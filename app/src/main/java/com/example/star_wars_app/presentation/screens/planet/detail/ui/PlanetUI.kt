package com.example.star_wars_app.presentation.screens.planet.detail.ui

import com.example.star_wars_app.domain.model.Film
import com.example.star_wars_app.domain.model.Character
import com.example.star_wars_app.domain.model.Planet

data class PlanetUI(
    val planet: Planet? = null,
    val characters: List<Character> = emptyList(),
    val films: List<Film> = emptyList()
)