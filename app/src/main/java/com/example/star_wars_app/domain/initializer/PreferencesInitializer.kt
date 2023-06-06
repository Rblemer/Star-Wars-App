package com.example.star_wars_app.domain.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.star_wars_app.data.local.preferences.PreferencesWrapper

class PreferencesInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        PreferencesWrapper.initPreferences(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}