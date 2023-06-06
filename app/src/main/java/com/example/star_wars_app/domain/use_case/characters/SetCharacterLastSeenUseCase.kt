package com.example.star_wars_app.domain.use_case.characters

import com.example.star_wars_app.commom.util.date.DateUtils
import com.example.star_wars_app.data.repository.CharactersRepository
import com.example.star_wars_app.domain.model.Character
import kotlinx.coroutines.flow.Flow

class SetCharacterLastSeenUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(character: Character): Flow<Character> {
        val now = DateUtils.getCurrentDate()
        return repository.updateItem(character.copy(lastSeen = now))
    }
}