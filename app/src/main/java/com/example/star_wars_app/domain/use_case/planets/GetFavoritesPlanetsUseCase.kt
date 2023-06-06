package com.example.star_wars_app.domain.use_case.planets

import com.example.star_wars_app.data.repository.PlanetsRepository

class GetFavoritesPlanetsUseCase(
    private val repository: PlanetsRepository,
) {
    operator fun invoke() = repository.getFavoriteItems()
}