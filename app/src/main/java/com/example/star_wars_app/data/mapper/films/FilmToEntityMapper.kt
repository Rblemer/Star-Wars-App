package com.example.star_wars_app.data.mapper.films

import com.example.star_wars_app.commom.mapper.Mapper
import com.example.star_wars_app.data.local.model.FilmEntity
import com.example.star_wars_app.data.remote.model.FilmModel

class FilmToEntityMapper : Mapper<FilmModel, FilmEntity> {
    override fun map(input: FilmModel) = input.toEntity()
}