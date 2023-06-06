package com.example.star_wars_app.domain.use_case.characters

import com.example.star_wars_app.data.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke(url: String, clearLocalDataSource: Boolean = false) =
        repository.getItemList(url, clearLocalDataSource)
}