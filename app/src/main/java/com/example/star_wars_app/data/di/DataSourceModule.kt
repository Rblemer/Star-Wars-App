package com.example.star_wars_app.data.di

import com.example.star_wars_app.data.local.data_source.CharactersLocalDataSource
import com.example.star_wars_app.data.local.data_source.FilmsLocalDataSource
import com.example.star_wars_app.data.local.data_source.PlanetsLocalDataSource
import com.example.star_wars_app.data.remote.data_sources.StarWarsRemoteDataSource
import com.example.star_wars_app.data.remote.data_sources.StarWarsRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single<StarWarsRemoteDataSource> { StarWarsRemoteDataSourceImpl(get()) }

    single { CharactersLocalDataSource(get()) }

    single { FilmsLocalDataSource(get()) }

    single { PlanetsLocalDataSource(get()) }

}