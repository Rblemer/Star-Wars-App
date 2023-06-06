package com.example.star_wars_app.presentation.screens.home.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.star_wars_app.commom.extension.symmetricDifference
import com.example.star_wars_app.data.mapper.characters.toSmallModel
import com.example.star_wars_app.data.mapper.films.toSmallModel
import com.example.star_wars_app.data.mapper.planets.toSmallModel
import com.example.star_wars_app.domain.use_case.characters.GetFavoriteCharactersUseCase
import com.example.star_wars_app.domain.use_case.films.GetFavoriteFilmsUseCase
import com.example.star_wars_app.domain.use_case.planets.GetFavoritesPlanetsUseCase
import com.example.star_wars_app.presentation.model.SmallItemModel
import com.example.star_wars_app.presentation.model.StateUI
import com.example.star_wars_app.presentation.screens.home.favorites.ui.FavoritesUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesPlanetsUseCase: GetFavoritesPlanetsUseCase,
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
    private val getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase,
) : ViewModel() {

    private val _favoritesUI = mutableStateOf(FavoritesUI())
    val favoritesUI: State<FavoritesUI> = _favoritesUI

    private val _charactersState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    private val _filmsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _planetsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val planetsState = _planetsState.asStateFlow()

    fun loadFavorites() {
        loadCharacters()
        loadFilms()
        loadPlanets()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            getFavoriteCharactersUseCase().onStart {
                _charactersState.emit(StateUI.Processing())
            }.catch {
                _charactersState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val models = data.map { it.toSmallModel() }
                val hasDifference =
                    (models symmetricDifference _favoritesUI.value.characters).isNotEmpty()
                if (hasDifference) {
                    _favoritesUI.value = favoritesUI.value.copy(
                        characters = models
                    )
                }
                if (favoritesUI.value.characters.isEmpty()) {
                    _charactersState.emit(StateUI.Error("You have no favorites characters"))
                } else {
                    _charactersState.emit(StateUI.Processed(models))
                }
            }
        }
    }

    private fun loadFilms() {
        viewModelScope.launch {
            getFavoriteFilmsUseCase().onStart {
                _filmsState.emit(StateUI.Processing())
            }.catch {
                _filmsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val models = data.map { it.toSmallModel() }
                val hasDifference =
                    (models symmetricDifference _favoritesUI.value.films).isNotEmpty()
                if (hasDifference) {
                    _favoritesUI.value = favoritesUI.value.copy(
                        films = models
                    )
                }
                if (favoritesUI.value.films.isEmpty()) {
                    _filmsState.emit(StateUI.Error("You have no favorites planets"))
                } else {
                    _filmsState.emit(StateUI.Processed(models))
                }
            }
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            getFavoritesPlanetsUseCase().onStart {
                _planetsState.emit(StateUI.Processing())
            }.catch {
                _planetsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val models = data.map { it.toSmallModel() }
                val hasDifference =
                    (models symmetricDifference _favoritesUI.value.planets).isNotEmpty()
                if (hasDifference) {
                    _favoritesUI.value = favoritesUI.value.copy(
                        planets = models
                    )
                }
                if (favoritesUI.value.planets.isEmpty()) {
                    _planetsState.emit(StateUI.Error("You have no favorites planets"))
                } else {
                    _planetsState.emit(StateUI.Processed(models))
                }
            }
        }
    }

}