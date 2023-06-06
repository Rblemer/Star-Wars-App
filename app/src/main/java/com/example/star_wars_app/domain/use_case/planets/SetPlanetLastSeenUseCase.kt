package com.example.star_wars_app.domain.use_case.planets

import com.example.star_wars_app.commom.util.date.DateUtils
import com.example.star_wars_app.data.repository.PlanetsRepository
import com.example.star_wars_app.domain.model.Planet
import kotlinx.coroutines.flow.Flow

class SetPlanetLastSeenUseCase(
    private val repository: PlanetsRepository
) {
    operator fun invoke(planet: Planet): Flow<Planet> {
        val now = DateUtils.getCurrentDate()
        return repository.updateItem(planet.copy(lastSeen = now))
    }
}