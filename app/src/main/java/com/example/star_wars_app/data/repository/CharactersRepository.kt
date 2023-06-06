package com.example.star_wars_app.data.repository

import com.example.star_wars_app.commom.extension.idFromUrl
import com.example.star_wars_app.commom.extension.pagedListOf
import com.example.star_wars_app.commom.mapper.NullableListMapper
import com.example.star_wars_app.commom.mapper.PagedListMapper
import com.example.star_wars_app.data.local.data_source.CharactersLocalDataSource
import com.example.star_wars_app.data.local.model.CharacterEntity
import com.example.star_wars_app.data.local.preferences.PreferencesWrapper
import com.example.star_wars_app.data.mapper.characters.toEntity
import com.example.star_wars_app.data.mapper.characters.toCharacter
import com.example.star_wars_app.data.remote.data_sources.StarWarsRemoteDataSource
import com.example.star_wars_app.data.remote.model.CharacterModel
import com.example.star_wars_app.data.remote.util.NetworkManager
import com.example.star_wars_app.domain.model.PagedList
import com.example.star_wars_app.domain.model.Character
import com.example.star_wars_app.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRepository(
    private val remoteDataSource: StarWarsRemoteDataSource,
    private val localDataSource: CharactersLocalDataSource,
    private val characterModelToEntityMapper: PagedListMapper<CharacterModel, CharacterEntity>,
    private val peopleEntityToCharacterMapper: NullableListMapper<CharacterEntity, Character>,
    private val networkManager: NetworkManager,
) : StarWarsRepository<Character> {

    private val preferences = PreferencesWrapper.getInstance()

    override fun getItemList(url: String, clearLocalDatasource: Boolean): Flow<PagedList<Character>> {
        return flow {
            if (clearLocalDatasource)
                localDataSource.clearEntities()
            if (preferences.isCharactersUpToDate()) {
                emit(pagedListOf(getLocalCharacters()))
            } else if (networkManager.hasInternetConnection()) {
                val remoteCharacters = remoteDataSource.getCharacters(url)
                localDataSource.insertEntities(characterModelToEntityMapper.map(remoteCharacters).results)
                if (remoteCharacters.next == null)
                    preferences.charactersIsUpToDate()
                emit(
                    PagedList(
                        next = remoteCharacters.next,
                        previous = remoteCharacters.previous,
                        results = getLocalCharacters()
                    )
                )
            } else {
                emit(pagedListOf(getLocalCharacters()))
            }
        }
    }

    override fun getItemByUrl(url: String): Flow<Character> {
        return flow {
            if (localDataSource.containsEntity(url.idFromUrl())) {
                emit(getLocalCharacterByUrl(url))
            } else if (networkManager.hasInternetConnection()) {
                val remoteCharacter = remoteDataSource.getCharacterByUrl(url)
                localDataSource.insertEntities(listOf(remoteCharacter.toEntity()))
                val localCharacter = getLocalCharacterByUrl(remoteCharacter.url.orEmpty())
                emit(localCharacter)
            } else {
                getLocalCharacterByUrl(url)
            }
        }
    }

    override fun getFavoriteItems(): Flow<List<Character>> {
        return flow { emit(peopleEntityToCharacterMapper.map(localDataSource.getFavoriteEntities())) }
    }

    override fun getLastSeenItems(): Flow<List<Character>> {
        return flow { emit(peopleEntityToCharacterMapper.map(localDataSource.getLastSeenEntities())) }
    }

    override fun updateItem(item: Character): Flow<Character> {
        return flow { emit(localDataSource.updateEntity(item.toEntity()).toCharacter()) }
    }

    private suspend fun getLocalCharacters() =
        peopleEntityToCharacterMapper.map(localDataSource.getEntities())

    private suspend fun getLocalCharacterByUrl(url: String) =
        localDataSource.getEntityById(url.idFromUrl()).toCharacter()

}