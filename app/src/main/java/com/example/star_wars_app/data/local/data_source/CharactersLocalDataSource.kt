package com.example.star_wars_app.data.local.data_source

import com.example.star_wars_app.commom.contants.Constants
import com.example.star_wars_app.commom.util.date.DateUtils
import com.example.star_wars_app.data.local.dao.CharactersDao
import com.example.star_wars_app.data.local.model.CharacterEntity
import com.example.star_wars_app.data.local.model.UpdateEntity
import com.example.star_wars_app.data.local.preferences.PreferencesWrapper

class CharactersLocalDataSource(
    private val dao: CharactersDao
) : StarWarsLocalDataSource<CharacterEntity> {
    override suspend fun getEntities() = dao.getCharacters().sortedBy { it.id }

    override suspend fun getEntityById(id: Int) = dao.getCharacterById(id)

    override suspend fun containsEntity(id: Int) = dao.containsCharacter(id) == 1

    override suspend fun clearEntities() {
        PreferencesWrapper.getInstance().clearCharacters()
        dao.clearCharacters()
    }

    override suspend fun getFavoriteEntities() = getEntities().filter { it.favorite }

    override suspend fun getLastSeenEntities(): List<CharacterEntity> {
        return getEntities().filter { it.lastSeen != null }.filter {
            val days = DateUtils.daysUntilToday(it.lastSeen ?: return@filter false)
            days <= Constants.LAST_SEEN_DAYS_INTERVAL
        }
    }

    override suspend fun updateEntity(entity: UpdateEntity): CharacterEntity {
        dao.updateCharacter(entity)
        return dao.getCharacterById(entity.id)
    }

    override suspend fun insertEntities(characters: List<CharacterEntity>) {
        val currentCharacters = getEntities().map { it.id }
        val newCharacters = characters.filter { !currentCharacters.contains(it.id) }
        dao.insertNewCharacters(newCharacters)
    }

}