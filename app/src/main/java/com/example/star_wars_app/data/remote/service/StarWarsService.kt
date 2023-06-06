package com.example.star_wars_app.data.remote.service

import com.example.star_wars_app.data.remote.model.FilmModel
import com.example.star_wars_app.data.remote.model.CharacterModel
import com.example.star_wars_app.data.remote.model.PlanetModel
import com.example.star_wars_app.data.remote.model.base.PagedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface StarWarsService {

    @GET
    suspend fun getCharacters(@Url url: String): Response<PagedListResponse<CharacterModel>>

    @GET
    suspend fun getCharacterByUrl(@Url url: String): Response<CharacterModel>

    @GET
    suspend fun getFilms(@Url url: String): Response<PagedListResponse<FilmModel>>

    @GET
    suspend fun getFilmByUrl(@Url url: String): Response<FilmModel>

    @GET
    suspend fun getPlanets(@Url url: String): Response<PagedListResponse<PlanetModel>>

    @GET
    suspend fun getPlanetByUrl(@Url url: String): Response<PlanetModel>

}