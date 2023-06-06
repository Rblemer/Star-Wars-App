package com.example.star_wars_app.commom.mapper

import com.example.star_wars_app.data.remote.model.base.PagedListResponse
import com.example.star_wars_app.domain.model.PagedList

class PagedListMapperImpl<I, O>(
    private val listMapper: NullableListMapper<I, O>
) : PagedListMapper<I, O> {

    override fun map(input: PagedListResponse<I>) = input.run {
            PagedList(
                next = next,
                previous = previous,
                results = listMapper.map(results)
            )
        }

}