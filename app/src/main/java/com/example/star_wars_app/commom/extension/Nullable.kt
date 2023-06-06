package com.example.star_wars_app.commom.extension

infix fun <T> T?.ifNull(other: T): T = this ?: other