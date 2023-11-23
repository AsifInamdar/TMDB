package com.asif.tmdb.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {

    data object HomeScreen : Screens("home")

    data object MoviesListGrid : Screens(
        route = "movie_list",
        navArguments = listOf(navArgument("type") {
            type = NavType.StringType
        })
    ){
        fun createRoute(movieType: String) = "movie_list/${movieType}"
    }

}