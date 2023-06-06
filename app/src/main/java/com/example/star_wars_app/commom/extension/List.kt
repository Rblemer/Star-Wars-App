package com.example.star_wars_app.commom.extension

import com.example.star_wars_app.domain.model.PagedList

fun <T> pagedListOf(list: List<T>? = null) = PagedList(
    next = null,
    previous = null,
    results = list ifNull emptyList()
)

infix fun <E> Collection<E>.symmetricDifference(other: Collection<E>): List<E> {
    val left = this subtract other.toSet()
    val right = other subtract this.toSet()
    return (left union right).toList()
}