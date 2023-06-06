package com.example.star_wars_app.data.mapper.films

import androidx.compose.ui.layout.ContentScale
import com.example.star_wars_app.commom.extension.idFromUrl
import com.example.star_wars_app.commom.extension.ifNull
import com.example.star_wars_app.commom.extension.toDate
import com.example.star_wars_app.commom.extension.toRoman
import com.example.star_wars_app.data.local.model.FilmEntity
import com.example.star_wars_app.data.local.model.UpdateEntity
import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.data.remote.model.FilmModel
import com.example.star_wars_app.domain.model.Film
import com.example.star_wars_app.presentation.model.ItemModel
import com.example.star_wars_app.presentation.model.SmallItemModel

fun FilmEntity.toFilm() = this.run {
    Film(
        id = id,
        title = title,
        characters = characters,
        url = url,
        species = species,
        starships = starships,
        vehicles = vehicles,
        planets = planets,
        director = director,
        episodeId = episodeId,
        openingCrawl = openingCrawl,
        producer = producer,
        releaseDate = releaseDate,
        image = image,
        lastSeen = lastSeen,
        favorite = favorite
    )
}

fun FilmModel.toEntity() = this.run {
    val id = url?.idFromUrl()
    FilmEntity(
        id = id ifNull 0,
        title = "Episode ${episodeId?.toRoman()}: $title",
        characters = characters.orEmpty(),
        url = url.orEmpty(),
        species = species.orEmpty(),
        starships = starships.orEmpty(),
        vehicles = vehicles.orEmpty(),
        planets = planets.orEmpty(),
        director = director.orEmpty(),
        episodeId = episodeId ifNull 0,
        openingCrawl = openingCrawl.orEmpty(),
        producer = producer.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}films/${id}.jpg",
        favorite = false,
        lastSeen = null
    )
}

fun Film.toEntity() = UpdateEntity(
    id = id,
    favorite = favorite,
    lastSeen = lastSeen
)

fun Film.toSmallModel() = SmallItemModel(
    url = url,
    image = image,
    name = "Episode ${episodeId.toRoman()}"
)

fun Film.toModel() = ItemModel(
    url = url,
    image = image,
    firstFields = listOf(
        title to "",
        "Director" to director,
        "Release date" to releaseDate.toDate()
    ),
    contentScale = ContentScale.FillWidth,
    aspectRatio = 4f / 5.5f,
    otherFields = listOf(
        "Producer" to producer
    )
)