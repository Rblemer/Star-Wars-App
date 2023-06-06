package com.example.star_wars_app.domain.use_case.characters

import com.example.star_wars_app.data.repository.CharactersRepository
import com.example.star_wars_app.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetCharacterByUrlUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke(url: String): Flow<Character> {
        return repository.getItemByUrl(url)
    }
}