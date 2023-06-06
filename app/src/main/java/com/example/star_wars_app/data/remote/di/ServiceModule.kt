package com.example.star_wars_app.data.remote.di

import com.example.star_wars_app.data.remote.config.ApiUrls
import com.example.star_wars_app.data.remote.service.ServiceManager
import com.example.star_wars_app.data.remote.service.StarWarsService
import org.koin.dsl.module

val apiModule = module {

    single { ServiceManager(ApiUrls.baseUrl) }

    single { get<ServiceManager>().create(StarWarsService::class.java) }

}
