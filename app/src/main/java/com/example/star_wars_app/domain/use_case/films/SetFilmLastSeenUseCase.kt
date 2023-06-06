package com.example.star_wars_app.domain.use_case.films

import com.example.star_wars_app.commom.util.date.DateUtils
import com.example.star_wars_app.data.repository.FilmsRepository
import com.example.star_wars_app.domain.model.Film
import kotlinx.coroutines.flow.Flow

class SetFilmLastSeenUseCase(
    private val repository: FilmsRepository
) {
    operator fun invoke(film: Film): Flow<Film> {
        val now = DateUtils.getCurrentDate()
        return repository.updateItem(film.copy(lastSeen = now))
    }
}