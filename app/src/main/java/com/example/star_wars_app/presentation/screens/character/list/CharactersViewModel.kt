package com.example.star_wars_app.presentation.screens.character.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.domain.model.Character
import com.example.star_wars_app.domain.model.PagedList
import com.example.star_wars_app.domain.use_case.characters.GetCharactersUseCase
import com.example.star_wars_app.presentation.model.StateUI
import com.example.star_wars_app.presentation.screens.character.list.ui.CharactersUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _charactersUI = mutableStateOf(CharactersUI())
    val characters: State<CharactersUI> = _charactersUI

    private val _charactersState = MutableStateFlow<StateUI<PagedList<Character>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    private val _loadMoreState = MutableStateFlow<StateUI<PagedList<Character>>>(StateUI.Idle())
    val loadMoreState = _loadMoreState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            getCharactersUseCase(url = ApiUrls.characters).onStart {
                _charactersState.emit(StateUI.Processing())
            }.catch {
                _charactersState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _charactersUI.value = characters.value.copy(
                    characters = data.results,
                    nextPage = data.next
                )
                _charactersState.emit(StateUI.Processed(data))
            }
        }
    }

    fun loadMoreCharacters() {
        viewModelScope.launch {
            _charactersUI.value.nextPage?.let { nextPage ->
                getCharactersUseCase(url = nextPage, clearLocalDataSource = false).onStart {
                    _loadMoreState.emit(StateUI.Processing())
                }.catch {
                    _loadMoreState.emit(StateUI.Error(it.message.orEmpty()))
                }.collect { data ->
                    _charactersUI.value = characters.value.copy(
                        characters = data.results,
                        nextPage = data.next
                    )
                    _loadMoreState.emit(StateUI.Processed(data))
                }
            }
        }
    }

}