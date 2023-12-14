package com.asif.tmdb.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asif.tmdb.utils.MovieType
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
            HomeMain(
                onHeaderButtonClick = { title ->
                    logD("navigate", "title $title")
                    navController.navigate(Screens.MoviesListGrid.createRoute(title))
                },
                navController = navController
            )
        }

        composable(route = Screens.MoviesListGrid.route + "/{type}",
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )) { entry ->

            val movieTypeString = entry.arguments?.getString("type").toString()
            val movieType = MovieType.entries.find { it.displayName.equals(movieTypeString, ignoreCase = true) }
                ?: MovieType.POPULAR // Default to POPULAR if no match is found


            MoviesListGrid(
                title = movieType.displayName,
                navController = navController
            )
        }

        composable(route = Screens.MovieDetailsScreen.route + "/{title}/{id}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.IntType
                }
            )) { entry ->
            MovieDetailsScreen(
                title = entry.arguments?.getString("title").toString(),
                id = entry.arguments?.getInt("id")!!,
                navController = navController
            )
        }

    }
}