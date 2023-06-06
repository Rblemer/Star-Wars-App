package com.example.star_wars_app.data.mapper.characters

import androidx.compose.ui.layout.ContentScale
import com.example.star_wars_app.commom.extension.idFromUrl
import com.example.star_wars_app.commom.extension.ifNull
import com.example.star_wars_app.data.local.model.CharacterEntity
import com.example.star_wars_app.data.local.model.UpdateEntity
import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.data.remote.model.CharacterModel
import com.example.star_wars_app.presentation.model.ItemModel
import com.example.star_wars_app.domain.model.Character
import com.example.star_wars_app.presentation.model.SmallItemModel

fun CharacterEntity.toCharacter() = this.run {
    val id = url.idFromUrl()
    Character(
        id = id,
        name = name,
        height = height,
        birthYear = birthYear,
        gender = gender,
        hairColor = hairColor,
        mass = mass,
        homeWorld = homeWorld,
        skinColor = skinColor,
        vehicles = vehicles,
        starships = starships,
        films = films,
        url = url,
        image = image,
        favorite = favorite,
        lastSeen = lastSeen
    )
}

fun CharacterModel.toEntity() = this.run {
    val id = url?.idFromUrl()
    CharacterEntity(
        id = id ifNull 0,
        name = name.orEmpty(),
        height = height.orEmpty(),
        birthYear = birthYear.orEmpty(),
        gender = gender.orEmpty(),
        hairColor = hairColor.orEmpty(),
        mass = mass.orEmpty(),
        homeWorld = homeWorld.orEmpty(),
        skinColor = skinColor.orEmpty(),
        vehicles = vehicles.orEmpty(),
        starships = starships.orEmpty(),
        films = films.orEmpty(),
        url = url.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}characters/$id.jpg",
        lastSeen = null,
        favorite = false
    )
}

fun Character.toSmallModel() = SmallItemModel(
    url = url,
    image = image,
    name = name
)

fun Character.toModel() = ItemModel(
    url = url,
    image = image,
    firstFields = listOf(
        "Name" to name,
        "Height" to height,
        "Mass" to mass
    ),
    contentScale = ContentScale.FillWidth,
    aspectRatio = 4f / 5f,
    otherFields = listOf(
        "Gender" to gender,
        "Hair color" to hairColor,
        "Birth yeah" to birthYear,
        "Skin color" to skinColor
    )
)

fun Character.toEntity() = UpdateEntity(
    id = id,
    lastSeen = lastSeen,
    favorite = favorite
)

val charactersEntity = listOf(
    CharacterEntity(
        name = "Luke Skywalker",
        height = "172",
        mass = "77",
        hairColor = "blond",
        skinColor = "fair",
        birthYear = "19BBY",
        gender = "male",
        homeWorld = "https://swapi.dev/api/planets/1/",
        films = listOf(
            "https://swapi.dev/api/films/1/",
            "https://swapi.dev/api/films/2/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/6/"
        ),
        vehicles = listOf(
            "https://swapi.dev/api/vehicles/14/",
            "https://swapi.dev/api/vehicles/30/"
        ),
        starships = listOf(
            "https://swapi.dev/api/starships/12/",
            "https://swapi.dev/api/starships/22/"
        ),
        url = "https://swapi.dev/api/people/1/",
        favorite = false,
        lastSeen = null,
        id = 99,
        image = "${ApiUrls.imageBaseUrl}characters/99.jpg",
    )
)