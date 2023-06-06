package com.example.star_wars_app.commom.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}