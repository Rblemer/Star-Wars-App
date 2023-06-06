package com.example.star_wars_app.domain.model

import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.presentation.compose.navigation.Screens

enum class Categories(val title: String, val url: String, val image: String, val route: String) {
    Characters(
        title = "Characters",
        url = ApiUrls.characters,
        image = "${ApiUrls.imageBaseUrl}/categories/character.jpg",
        route = Screens.Characters.route
    ),
    Films(
        title = "Movies",
        url = ApiUrls.films,
        image = "${ApiUrls.imageBaseUrl}/categories/films.jpg",
        route = Screens.Films.route
    ),
    Planets(
        title = "Planets",
        url = ApiUrls.planets,
        image = "${ApiUrls.imageBaseUrl}/categories/planets.jpg",
        route = Screens.Planets.route
    );
}