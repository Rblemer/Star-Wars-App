package com.example.star_wars_app.domain.use_case.films

import com.example.star_wars_app.data.repository.FilmsRepository

class GetFavoriteFilmsUseCase(
    private val repository: FilmsRepository,
) {
    operator fun invoke() = repository.getFavoriteItems()
}