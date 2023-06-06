package com.example.star_wars_app.data.local.data_source

import com.example.star_wars_app.data.local.model.UpdateEntity

interface StarWarsLocalDataSource <T> {

    suspend fun getEntities(): List<T>

    suspend fun getEntityById(id: Int): T

    suspend fun containsEntity(id: Int): Boolean

    suspend fun insertEntities(characters: List<T>)

    suspend fun clearEntities()

    suspend fun getFavoriteEntities(): List<T>

    suspend fun getLastSeenEntities(): List<T>

    suspend fun updateEntity(entity: UpdateEntity): T

}