package com.example.star_wars_app.presentation.screens.film.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.domain.model.Film
import com.example.star_wars_app.domain.model.PagedList
import com.example.star_wars_app.domain.use_case.films.GetFilmsUseCase
import com.example.star_wars_app.presentation.model.StateUI
import com.example.star_wars_app.presentation.screens.film.list.ui.FilmsUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val getFilmsUseCase: GetFilmsUseCase
) : ViewModel() {

    private val _filmsUI = mutableStateOf(FilmsUI())
    val filmsUI: State<FilmsUI> = _filmsUI

    private val _filmsState = MutableStateFlow<StateUI<PagedList<Film>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    init {
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launch {
            getFilmsUseCase(url = ApiUrls.films).onStart {
                _filmsState.emit(StateUI.Processing())
            }.catch {
                _filmsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _filmsUI.value = filmsUI.value.copy(
                    films = data.results,
                    nextPage = data.next
                )
                _filmsState.emit(StateUI.Processed(data))
            }
        }
    }

}