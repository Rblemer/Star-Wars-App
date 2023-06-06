package com.example.star_wars_app.data.di

import com.example.star_wars_app.data.mapper.characters.CharacterToEntityMapper
import com.example.star_wars_app.data.mapper.characters.CharacterEntityToCharacterMapper
import com.example.star_wars_app.data.mapper.films.FilmToEntityMapper
import com.example.star_wars_app.data.mapper.films.FilmEntityToFilmMapper
import com.example.star_wars_app.data.mapper.planets.PlanetToEntityMapper
import com.example.star_wars_app.data.mapper.planets.PlanetEntityToPlanetMapper
import org.koin.dsl.module

val mapperModule = module {

    single { CharacterToEntityMapper() }

    single { CharacterEntityToCharacterMapper() }

    single { FilmToEntityMapper() }

    single { FilmEntityToFilmMapper() }

    single { PlanetEntityToPlanetMapper() }

    single { PlanetToEntityMapper() }

}