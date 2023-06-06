package com.example.star_wars_app.data.repository

import com.example.star_wars_app.commom.extension.idFromUrl
import com.example.star_wars_app.commom.extension.pagedListOf
import com.example.star_wars_app.commom.mapper.NullableListMapper
import com.example.star_wars_app.commom.mapper.PagedListMapper
import com.example.star_wars_app.data.local.data_source.PlanetsLocalDataSource
import com.example.star_wars_app.data.local.model.PlanetEntity
import com.example.star_wars_app.data.local.preferences.PreferencesWrapper
import com.example.star_wars_app.data.mapper.planets.toEntity
import com.example.star_wars_app.data.mapper.planets.toPlanet
import com.example.star_wars_app.data.remote.data_sources.StarWarsRemoteDataSource
import com.example.star_wars_app.data.remote.model.PlanetModel
import com.example.star_wars_app.data.remote.util.NetworkManager
import com.example.star_wars_app.domain.model.PagedList
import com.example.star_wars_app.domain.model.Planet
import com.example.star_wars_app.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlanetsRepository(
    private val remoteDataSource: StarWarsRemoteDataSource,
    private val localDataSource: PlanetsLocalDataSource,
    private val planetModelToEntityMapper: PagedListMapper<PlanetModel, PlanetEntity>,
    private val planetEntityToPlanetMapper: NullableListMapper<PlanetEntity, Planet>,
    private val networkManager: NetworkManager,
) : StarWarsRepository<Planet> {

    private val preferences = PreferencesWrapper.getInstance()

    override fun getItemList(url: String, clearLocalDatasource: Boolean): Flow<PagedList<Planet>> {
        return flow {
            if (clearLocalDatasource) localDataSource.clearEntities()
            if (preferences.isPlanetsUpToDate()) {
                emit(pagedListOf(getLocalPlanets()))
            } else if (networkManager.hasInternetConnection()) {
                val remotePlanets = remoteDataSource.getPlanets(url)
                localDataSource.insertEntities(planetModelToEntityMapper.map(remotePlanets).results)
                if (remotePlanets.next == null)
                    preferences.planetsIsUpToDate()
                emit(
                    PagedList(
                        next = remotePlanets.next,
                        previous = remotePlanets.previous,
                        results = getLocalPlanets()
                    )
                )
            } else {
                emit(pagedListOf(getLocalPlanets()))
            }
        }
    }

    override fun getItemByUrl(url: String): Flow<Planet> {
        return flow {
            if (localDataSource.containsEntity(url.idFromUrl())) {
                emit(getLocalPlanetByUrl(url))
            } else if (networkManager.hasInternetConnection()) {
                val remotePlanets = remoteDataSource.getPlanetByUrl(url)
                localDataSource.insertEntities(listOf(remotePlanets.toEntity()))
                val localPlanet = getLocalPlanetByUrl(remotePlanets.url.orEmpty())
                emit(localPlanet)
            } else {
                getLocalPlanetByUrl(url)
            }
        }
    }

    override fun getFavoriteItems(): Flow<List<Planet>> {
        return flow { emit(planetEntityToPlanetMapper.map(localDataSource.getFavoriteEntities())) }
    }

    override fun getLastSeenItems(): Flow<List<Planet>> {
        return flow { emit(planetEntityToPlanetMapper.map(localDataSource.getLastSeenEntities())) }
    }

    override fun updateItem(item: Planet): Flow<Planet> {
        return flow { emit(localDataSource.updateEntity(item.toEntity()).toPlanet()) }
    }

    private suspend fun getLocalPlanets() =
        planetEntityToPlanetMapper.map(localDataSource.getEntities())

    private suspend fun getLocalPlanetByUrl(url: String) =
        localDataSource.getEntityById(url.idFromUrl()).toPlanet()

}