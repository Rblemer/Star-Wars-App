package com.example.star_wars_app.domain.repository

import com.example.star_wars_app.domain.model.PagedList
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository<T> {

    fun getItemList(url: String, clearLocalDatasource: Boolean): Flow<PagedList<T>>

    fun getItemByUrl(url: String): Flow<T>

    fun updateItem(item: T): Flow<T>

    fun getFavoriteItems(): Flow<List<T>>

    fun getLastSeenItems(): Flow<List<T>>

}