package com.example.star_wars_app.commom.extension

import androidx.navigation.NavHostController
import com.example.star_wars_app.presentation.compose.navigation.Screens

fun NavHostController.backToHome() {
    this.navigate(Screens.Home.route) {
        popUpTo(0)
    }
}