package com.example.star_wars_app.commom.mapper

class NullableListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableListMapper<I, O> {
    override fun map(input: List<I>?): List<O> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}