package com.example.star_wars_app.presentation.screens.home.categories

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.star_wars_app.presentation.screens.home.categories.components.CategoryItemList

@Composable
fun CategoriesMainScreen(
    navHostController: NavHostController,
) {
    CategoryItemList(
        aspectRatio = 2f / 1f,
        onItemClick = { route ->
            navHostController.navigate(route)
        }
    )
}