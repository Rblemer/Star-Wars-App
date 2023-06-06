package com.example.star_wars_app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.star_wars_app.data.local.model.CharacterEntity
import com.example.star_wars_app.data.local.model.UpdateEntity

@Dao
interface CharactersDao {

    @Insert
    suspend fun insertNewCharacters(characters: List<CharacterEntity>)

    @Update(entity = CharacterEntity::class)
    suspend fun updateCharacter(characters: UpdateEntity)

    @Query("SELECT * FROM people")
    suspend fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity

    @Query("DELETE FROM people")
    suspend fun clearCharacters()

    @Query("SELECT COUNT(1) FROM people WHERE id = :id")
    suspend fun containsCharacter(id: Int): Int

}