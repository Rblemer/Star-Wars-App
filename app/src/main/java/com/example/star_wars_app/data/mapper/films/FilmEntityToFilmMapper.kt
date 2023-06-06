package com.example.star_wars_app.data.mapper.films

import com.example.star_wars_app.commom.mapper.Mapper
import com.example.star_wars_app.data.local.model.FilmEntity
import com.example.star_wars_app.domain.model.Film

class FilmEntityToFilmMapper : Mapper<FilmEntity, Film> {
    override fun map(input: FilmEntity) = input.toFilm()
}