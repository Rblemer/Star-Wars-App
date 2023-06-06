package com.example.star_wars_app.domain.use_case.films

import com.example.star_wars_app.data.repository.FilmsRepository
import com.example.star_wars_app.domain.model.Film
import kotlinx.coroutines.flow.Flow

class GetFilmByUrlUseCase(
    private val repository: FilmsRepository
) {
    operator fun invoke(url: String): Flow<Film> {
        return repository.getItemByUrl(url)
    }
}