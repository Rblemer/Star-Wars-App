package com.example.star_wars_app.domain.use_case.planets

import com.example.star_wars_app.data.repository.PlanetsRepository

class GetPlanetsUseCase(
    private val repository: PlanetsRepository,
) {
    operator fun invoke(url: String, clearLocalDatasource: Boolean = false) =
        repository.getItemList(url, clearLocalDatasource)
}