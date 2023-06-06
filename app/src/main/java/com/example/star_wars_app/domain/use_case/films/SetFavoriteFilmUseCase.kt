package com.example.star_wars_app.domain.use_case.films

import com.example.star_wars_app.data.repository.FilmsRepository
import com.example.star_wars_app.domain.model.Film
import kotlinx.coroutines.flow.Flow

class SetFavoriteFilmUseCase(
    private val repository: FilmsRepository
) {
    operator fun invoke(film: Film): Flow<Film> {
        return repository.updateItem(film.copy(favorite = !film.favorite))
    }
}