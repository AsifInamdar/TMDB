package com.asif.tmdb.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asif.tmdb.utils.logD

@Composable
fun TMDBApp() {
    val navController = rememberNavController()
    MyNavigationHost(navController)
}

@Composable
fun MyNavigationHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        composable(route = Screens.HomeScreen.route) {
            HomeMain(onMovieClick = { title ->
                logD("navigate", "title $title")
                navController.navigate(Screens.MoviesListGrid.createRoute(title))
            })
        }

        composable(route = Screens.MoviesListGrid.route + "/{type}",
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )) { entry ->
            MoviesListGrid(
                title = entry.arguments?.getString("type").toString(),
                navController = navController
            )
        }
    }
}