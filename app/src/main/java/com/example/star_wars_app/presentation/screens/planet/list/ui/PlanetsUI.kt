package com.example.star_wars_app.presentation.screens.planet.list.ui

import com.example.star_wars_app.domain.model.Planet

data class PlanetsUI(
    val planets: List<Planet> = emptyList(),
    val nextPage: String? = null
)