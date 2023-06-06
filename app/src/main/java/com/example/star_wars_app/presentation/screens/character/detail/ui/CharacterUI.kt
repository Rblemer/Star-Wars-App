package com.example.star_wars_app.presentation.screens.character.detail.ui

import com.example.star_wars_app.domain.model.Film
import com.example.star_wars_app.domain.model.Character
import com.example.star_wars_app.domain.model.Planet

data class CharacterUI(
    val character: Character? = null,
    val films: List<Film> = emptyList(),
    val homeWorld: Planet? = null
)