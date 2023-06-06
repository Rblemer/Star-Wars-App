package com.example.star_wars_app.data.repository

import com.example.star_wars_app.commom.extension.idFromUrl
import com.example.star_wars_app.commom.extension.pagedListOf
import com.example.star_wars_app.commom.mapper.NullableListMapper
import com.example.star_wars_app.commom.mapper.PagedListMapper
import com.example.star_wars_app.data.local.data_source.FilmsLocalDataSource
import com.example.star_wars_app.data.local.model.FilmEntity
import com.example.star_wars_app.data.local.preferences.PreferencesWrapper
import com.example.star_wars_app.data.mapper.films.toEntity
import com.example.star_wars_app.data.mapper.films.toFilm
import com.example.star_wars_app.data.remote.data_sources.StarWarsRemoteDataSource
import com.example.star_wars_app.data.remote.model.FilmModel
import com.example.star_wars_app.data.remote.util.NetworkManager
import com.example.star_wars_app.domain.model.Film
import com.example.star_wars_app.domain.model.PagedList
import com.example.star_wars_app.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmsRepository(
    private val remoteDataSource: StarWarsRemoteDataSource,
    private val localDataSource: FilmsLocalDataSource,
    private val filmModelToEntityMapper: PagedListMapper<FilmModel, FilmEntity>,
    private val filmEntityToPeopleMapper: NullableListMapper<FilmEntity, Film>,
    private val networkManager: NetworkManager,
) : StarWarsRepository<Film> {

    private val preferences = PreferencesWrapper.getInstance()

    private suspend fun getLocalFilms() =
        filmEntityToPeopleMapper.map(localDataSource.getEntities())

    private suspend fun getLocalFilmByUrl(url: String) =
        localDataSource.getEntityById(url.idFromUrl()).toFilm()

    override fun getItemList(url: String, clearLocalDatasource: Boolean): Flow<PagedList<Film>> {
        return flow {
            if (clearLocalDatasource) localDataSource.clearEntities()
            if (preferences.isFilmsUpToDate()) {
                emit(pagedListOf(getLocalFilms()))
            } else if (networkManager.hasInternetConnection()) {
                val remoteFilms = remoteDataSource.getFilms(url)
                localDataSource.insertEntities(filmModelToEntityMapper.map(remoteFilms).results)
                if (remoteFilms.next == null) {
                    preferences.filmsIsUpToDate()
                }
                emit(
                    PagedList(
                        next = remoteFilms.next,
                        previous = remoteFilms.previous,
                        results = getLocalFilms()
                    )
                )
            } else {
                emit(pagedListOf(getLocalFilms()))
            }
        }
    }

    override fun getItemByUrl(url: String): Flow<Film> {
        return flow {
            if (localDataSource.containsEntity(url.idFromUrl())) {
                emit(getLocalFilmByUrl(url))
            } else if (networkManager.hasInternetConnection()) {
                val remoteFilm = remoteDataSource.getFilmByUrl(url)
                localDataSource.insertEntities(listOf(remoteFilm.toEntity()))
                val localFilm = getLocalFilmByUrl(remoteFilm.url.orEmpty())
                emit(localFilm)
            } else {
                getLocalFilmByUrl(url)
            }
        }
    }

    override fun getFavoriteItems(): Flow<List<Film>> {
        return flow { emit(filmEntityToPeopleMapper.map(localDataSource.getFavoriteEntities())) }
    }

    override fun getLastSeenItems(): Flow<List<Film>> {
        return flow { emit(filmEntityToPeopleMapper.map(localDataSource.getLastSeenEntities())) }
    }

    override fun updateItem(item: Film): Flow<Film> {
        return flow { emit(localDataSource.updateEntity(item.toEntity()).toFilm()) }
    }

}