package com.example.star_wars_app.data.local.data_source

import com.example.star_wars_app.commom.contants.Constants
import com.example.star_wars_app.commom.util.date.DateUtils
import com.example.star_wars_app.data.local.dao.FilmsDao
import com.example.star_wars_app.data.local.model.FilmEntity
import com.example.star_wars_app.data.local.model.UpdateEntity
import com.example.star_wars_app.data.local.preferences.PreferencesWrapper

class FilmsLocalDataSource(
    private val dao: FilmsDao
) : StarWarsLocalDataSource<FilmEntity> {
    override suspend fun getEntities() = dao.getFilms().sortedBy { it.id }

    override suspend fun getEntityById(id: Int) = dao.getFilmById(id)

    override suspend fun containsEntity(id: Int) = dao.containsFilm(id) == 1

    override suspend fun clearEntities() {
        PreferencesWrapper.getInstance().clearFilms()
        dao.clearFilms()
    }

    override suspend fun getFavoriteEntities() = getEntities().filter { it.favorite }

    override suspend fun getLastSeenEntities(): List<FilmEntity> {
        return getEntities().filter { it.lastSeen != null }.filter {
            val days = DateUtils.daysUntilToday(it.lastSeen ?: return@filter false)
            days <= Constants.LAST_SEEN_DAYS_INTERVAL
        }
    }

    override suspend fun updateEntity(entity: UpdateEntity): FilmEntity {
        dao.updateFilm(entity)
        return dao.getFilmById(entity.id)
    }

    override suspend fun insertEntities(characters: List<FilmEntity>) {
        val currentCharacters = getEntities().map { it.id }
        val newCharacters = characters.filter { !currentCharacters.contains(it.id) }
        dao.insertNewFilms(newCharacters)
    }

}