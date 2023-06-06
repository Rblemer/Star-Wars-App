package com.example.star_wars_app.data.remote.data_sources

import com.example.star_wars_app.data.remote.model.FilmModel
import com.example.star_wars_app.data.remote.model.CharacterModel
import com.example.star_wars_app.data.remote.model.PlanetModel
import com.example.star_wars_app.data.remote.model.base.PagedListResponse
import com.example.star_wars_app.data.remote.service.StarWarsService
import retrofit2.HttpException

class StarWarsRemoteDataSourceImpl(
    private val service: StarWarsService
) : StarWarsRemoteDataSource {

    override suspend fun getCharacters(url: String): PagedListResponse<CharacterModel> {
        val response = service.getCharacters(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getFilms(url: String): PagedListResponse<FilmModel> {
        val response = service.getFilms(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getPlanets(url: String): PagedListResponse<PlanetModel> {
        val response = service.getPlanets(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getPlanetByUrl(url: String): PlanetModel {
        val response = service.getPlanetByUrl(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getFilmByUrl(url: String): FilmModel {
        val response = service.getFilmByUrl(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getCharacterByUrl(url: String): CharacterModel {
        val response = service.getCharacterByUrl(url)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }

}