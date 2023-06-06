package com.example.star_wars_app.presentation.screens.character.list.ui

import com.example.star_wars_app.domain.model.Character

data class CharactersUI(
    val characters: List<Character> = emptyList(),
    val nextPage: String? = null
)