package com.example.star_wars_app.domain.use_case.planets

import com.example.star_wars_app.data.repository.PlanetsRepository
import com.example.star_wars_app.domain.model.Planet
import kotlinx.coroutines.flow.Flow

class SetFavoritePlanetUseCase(
    private val repository: PlanetsRepository
) {
    operator fun invoke(planet: Planet): Flow<Planet> {
        return repository.updateItem(planet.copy(favorite = !planet.favorite))
    }
}