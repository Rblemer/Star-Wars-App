package com.example.star_wars_app.data.mapper.characters

import com.example.star_wars_app.commom.mapper.Mapper
import com.example.star_wars_app.data.local.model.CharacterEntity
import com.example.star_wars_app.domain.model.Character

class CharacterEntityToCharacterMapper : Mapper<CharacterEntity, Character> {
    override fun map(input: CharacterEntity) = input.toCharacter()
}