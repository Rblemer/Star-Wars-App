package com.example.star_wars_app.domain.di

import com.example.star_wars_app.presentation.screens.character.detail.CharacterDetailViewModel
import com.example.star_wars_app.presentation.screens.character.list.CharactersViewModel
import com.example.star_wars_app.presentation.screens.film.detail.FilmDetailViewModel
import com.example.star_wars_app.presentation.screens.film.list.FilmsViewModel
import com.example.star_wars_app.presentation.screens.home.favorites.FavoritesViewModel
import com.example.star_wars_app.presentation.screens.home.last_seen.LastSeenViewModel
import com.example.star_wars_app.presentation.screens.planet.detail.PlanetDetailViewModel
import com.example.star_wars_app.presentation.screens.planet.list.PlanetsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharactersViewModel(
            getCharactersUseCase = get()
        )
    }

    viewModel {
        FilmsViewModel(
            getFilmsUseCase = get()
        )
    }

    viewModel { (url: String) ->
        CharacterDetailViewModel(
            getCharacterByUrlUseCase = get(),
            getFilmByUrlUseCase = get(),
            getPlanetByUrlUseCase = get(),
            setCharacterLastSeenUseCase = get(),
            setFavoriteCharacterUseCase = get(),
            url = url
        )
    }

    viewModel { (url: String) ->
        FilmDetailViewModel(
            getCharacterByUrlUseCase = get(),
            getFilmByUrlUseCase = get(),
            getPlanetByUrlUseCase = get(),
            setFilmLastSeenUseCase = get(),
            setFavoriteFilmsUseCase = get(),
            url = url
        )
    }

    viewModel {
        PlanetsViewModel(
            getPlanetsUseCase = get()
        )
    }

    viewModel { (url: String) ->
        PlanetDetailViewModel(
            getPlanetByUrlUseCase = get(),
            getCharacterByUrlUseCase = get(),
            getFilmByUrlUseCase = get(),
            setFavoritePlanetUseCase = get(),
            setLastSeenUseCase = get(),
            url = url
        )
    }

    viewModel {
        FavoritesViewModel(
            getFavoriteCharactersUseCase = get(),
            getFavoriteFilmsUseCase = get(),
            getFavoritesPlanetsUseCase = get()
        )
    }

    viewModel {
        LastSeenViewModel(
            getLastSeenCharactersUseCase = get(),
            getLastSeenFilmsUseCase = get(),
            getLastSeenPlanetsUseCase = get()
        )
    }

}