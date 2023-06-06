package com.example.star_wars_app.presentation.screens.home.last_seen.ui

import com.example.star_wars_app.presentation.model.SmallItemModel

data class LastSeenUI(
    val characters: List<SmallItemModel> = emptyList(),
    val films: List<SmallItemModel> = emptyList(),
    val planets: List<SmallItemModel> = emptyList(),
)