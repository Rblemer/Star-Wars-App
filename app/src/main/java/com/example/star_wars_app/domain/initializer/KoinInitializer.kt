package com.example.star_wars_app.domain.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.star_wars_app.data.di.dataSourceModule
import com.example.star_wars_app.data.di.mapperModule
import com.example.star_wars_app.data.di.repositoryModule
import com.example.star_wars_app.data.local.di.databaseModule
import com.example.star_wars_app.data.remote.di.apiModule
import com.example.star_wars_app.domain.di.useCaseModule
import com.example.star_wars_app.domain.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(
                apiModule,
                dataSourceModule,
                mapperModule,
                viewModelModule,
                useCaseModule,
                repositoryModule,
                databaseModule
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}