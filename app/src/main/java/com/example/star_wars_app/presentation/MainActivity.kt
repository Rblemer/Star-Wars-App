package com.example.star_wars_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.star_wars_app.presentation.navGraph.NavHostScreen
import com.example.star_wars_app.presentation.compose.theme.StarWarsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsAppTheme {
                NavHostScreen()
            }
        }
    }
}