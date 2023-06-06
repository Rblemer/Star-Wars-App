package com.example.star_wars_app.data.mapper.planets

import com.example.star_wars_app.commom.mapper.Mapper
import com.example.star_wars_app.data.local.model.PlanetEntity
import com.example.star_wars_app.data.remote.model.PlanetModel

class PlanetToEntityMapper : Mapper<PlanetModel,  PlanetEntity> {
    override fun map(input: PlanetModel) = input.toEntity()
}