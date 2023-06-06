package com.example.star_wars_app.data.di

import com.example.star_wars_app.commom.mapper.NullableListMapperImpl
import com.example.star_wars_app.commom.mapper.PagedListMapperImpl
import com.example.star_wars_app.data.mapper.characters.CharacterToEntityMapper
import com.example.star_wars_app.data.mapper.characters.CharacterEntityToCharacterMapper
import com.example.star_wars_app.data.mapper.films.FilmToEntityMapper
import com.example.star_wars_app.data.mapper.films.FilmEntityToFilmMapper
import com.example.star_wars_app.data.mapper.planets.PlanetToEntityMapper
import com.example.star_wars_app.data.mapper.planets.PlanetEntityToPlanetMapper
import com.example.star_wars_app.data.remote.util.NetworkManager
import com.example.star_wars_app.data.repository.CharactersRepository
import com.example.star_wars_app.data.repository.FilmsRepository
import com.example.star_wars_app.data.repository.PlanetsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        NetworkManager(get())
    }

    single {
        CharactersRepository(
            characterModelToEntityMapper = PagedListMapperImpl(
                listMapper = NullableListMapperImpl(
                    mapper = get<CharacterToEntityMapper>()
                )
            ),
            peopleEntityToCharacterMapper = NullableListMapperImpl(
                mapper = get<CharacterEntityToCharacterMapper>()
            ),
            remoteDataSource = get(),
            localDataSource = get(),
            networkManager = get()
        )
    }

    single {
        FilmsRepository(
            filmModelToEntityMapper = PagedListMapperImpl(
                listMapper = NullableListMapperImpl(
                    mapper = get<FilmToEntityMapper>()
                )
            ),
            filmEntityToPeopleMapper = NullableListMapperImpl(
                mapper = get<FilmEntityToFilmMapper>()
            ),
            remoteDataSource = get(),
            localDataSource = get(),
            networkManager = get()
        )
    }

    single {
        PlanetsRepository(
            planetModelToEntityMapper = PagedListMapperImpl(
                listMapper = NullableListMapperImpl(
                    mapper = get<PlanetToEntityMapper>()
                )
            ),
            planetEntityToPlanetMapper = NullableListMapperImpl(
                mapper = get<PlanetEntityToPlanetMapper>()
            ),
            networkManager = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

}