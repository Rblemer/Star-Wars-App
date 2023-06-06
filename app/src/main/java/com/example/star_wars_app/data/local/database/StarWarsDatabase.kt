package com.example.star_wars_app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.star_wars_app.data.local.dao.CharactersDao
import com.example.star_wars_app.data.local.dao.FilmsDao
import com.example.star_wars_app.data.local.dao.PlanetsDao
import com.example.star_wars_app.data.local.model.FilmEntity
import com.example.star_wars_app.data.local.model.CharacterEntity
import com.example.star_wars_app.data.local.model.PlanetEntity
import com.example.star_wars_app.data.local.type_converter.StringListConverter

@Database(
    entities = [
        CharacterEntity::class,
        FilmEntity::class,
        PlanetEntity::class
    ],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract val planetsDao: PlanetsDao
    abstract val charactersDao: CharactersDao
    abstract val filmsDao: FilmsDao

}