package com.example.star_wars_app.data.mapper.planets

import com.example.star_wars_app.commom.mapper.Mapper
import com.example.star_wars_app.data.local.model.PlanetEntity
import com.example.star_wars_app.domain.model.Planet

class PlanetEntityToPlanetMapper : Mapper<PlanetEntity, Planet> {
    override fun map(input: PlanetEntity) = input.toPlanet()
}