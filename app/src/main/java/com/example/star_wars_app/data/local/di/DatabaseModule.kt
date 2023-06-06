package com.example.star_wars_app.data.local.di

import androidx.room.Room
import com.example.star_wars_app.data.local.database.StarWarsDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = get(),
            StarWarsDatabase::class.java,
            "starwarsdb"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<StarWarsDatabase>().charactersDao }

    single { get<StarWarsDatabase>().filmsDao }

    single { get<StarWarsDatabase>().planetsDao }
}