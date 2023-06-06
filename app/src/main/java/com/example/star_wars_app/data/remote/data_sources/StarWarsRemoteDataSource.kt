package com.example.star_wars_app.data.remote.data_sources

import com.example.star_wars_app.data.remote.model.FilmModel
import com.example.star_wars_app.data.remote.model.CharacterModel
import com.example.star_wars_app.data.remote.model.PlanetModel
import com.example.star_wars_app.data.remote.model.base.PagedListResponse

interface StarWarsRemoteDataSource {

    suspend fun getCharacters(url: String): PagedListResponse<CharacterModel>

    suspend fun getCharacterByUrl(url: String): CharacterModel

    suspend fun getFilms(url: String): PagedListResponse<FilmModel>

    suspend fun getFilmByUrl(url: String): FilmModel

    suspend fun getPlanets(url: String): PagedListResponse<PlanetModel>

    suspend fun getPlanetByUrl(url: String): PlanetModel

}