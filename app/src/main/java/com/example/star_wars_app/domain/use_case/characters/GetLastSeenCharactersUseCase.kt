package com.example.star_wars_app.domain.use_case.characters

import com.example.star_wars_app.data.repository.CharactersRepository

class GetLastSeenCharactersUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke() = repository.getLastSeenItems()
}