package com.example.star_wars_app.data.mapper.characters

import com.example.star_wars_app.commom.mapper.Mapper
import com.example.star_wars_app.data.local.model.CharacterEntity
import com.example.star_wars_app.data.remote.model.CharacterModel

class CharacterToEntityMapper : Mapper<CharacterModel, CharacterEntity> {
    override fun map(input: CharacterModel) = input.toEntity()
}