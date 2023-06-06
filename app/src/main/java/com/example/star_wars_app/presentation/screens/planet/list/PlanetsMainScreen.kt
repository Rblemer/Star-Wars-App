package com.example.star_wars_app.presentation.screens.planet.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.star_wars_app.commom.extension.backToHome
import com.example.star_wars_app.commom.extension.containsIgnoringAccent
import com.example.star_wars_app.data.mapper.planets.toModel
import com.example.star_wars_app.presentation.compose.components.lazy_list.PagedItemList
import com.example.star_wars_app.presentation.compose.navigation.Screens
import com.example.star_wars_app.presentation.compose.widgets.state.error.DefaultErrorScreen
import com.example.star_wars_app.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import com.example.star_wars_app.presentation.compose.widgets.top_bar.SearchTopBar
import com.example.star_wars_app.presentation.compose.widgets.top_bar.TopBar
import com.example.star_wars_app.presentation.compose.widgets.top_bar.TopBarIcon
import com.example.star_wars_app.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetsMainScreen(
    navHostController: NavHostController,
    viewModel: PlanetsViewModel,
) {
    BackHandler {
        navHostController.backToHome()
    }
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            if (isSearching) {
                SearchTopBar(
                    searchText = searchText,
                    placeholderText = "Planet name",
                    onClearClick = { searchText = "" },
                    onBackPressed = { isSearching = false },
                    onSearchTextChanged = {
                        searchText = it
                    }
                )
            } else {
                TopBar(
                    title = "Planets",
                    onBackPressed = { navHostController.backToHome() },
                    scrollBehavior = scrollBehavior,
                    actions = {
                        TopBarIcon(
                            onClick = { isSearching = true },
                            imageVector = Icons.Default.Search
                        )
                    }
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            val planetsUI = viewModel.planetsUI.value
            val loadMoreResponse = viewModel.loadMoreState.collectAsStateWithLifecycle().value
            viewModel.planetsState.collectAsStateWithLifecycle().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen(message = response.message)
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        val filteredPlanets = planetsUI.planets.filter {
                            if (searchText.isNotBlank()) {
                                it.name.containsIgnoringAccent(searchText, ignoreCase = true)
                            } else true
                        }
                        PagedItemList(
                            items = filteredPlanets.map { it.toModel() },
                            onItemClick = { url ->
                                navHostController.navigate(
                                    Screens.PlanetDetail.routeWithArgument(url)
                                )
                            },
                            isLoading = loadMoreResponse.loading(),
                            loadMore = {
                                val canLoadMore = planetsUI.nextPage != null
                                if (canLoadMore && !isSearching) {
                                    viewModel.loadMorePlanets()
                                }
                            }
                        )
                    }
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}