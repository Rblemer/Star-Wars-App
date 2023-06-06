package com.example.star_wars_app.presentation.screens.home.favorites.ui

import com.example.star_wars_app.presentation.model.SmallItemModel

data class FavoritesUI(
    val characters: List<SmallItemModel> = emptyList(),
    val films: List<SmallItemModel> = emptyList(),
    val planets: List<SmallItemModel> = emptyList(),
)