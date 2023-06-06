package com.example.star_wars_app.commom.mapper

import com.example.star_wars_app.data.remote.model.base.PagedListResponse
import com.example.star_wars_app.domain.model.PagedList

interface PagedListMapper<I, O> : Mapper<PagedListResponse<I>, PagedList<O>>