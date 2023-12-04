package com.asif.tmdb.compose

sealed class Screens(
    val route: String
) {

    data object HomeScreen : Screens("home")

    data object MoviesListGrid : Screens(
        route = "movie_list"
    ) {
        fun createRoute(movieType: String) = "movie_list/${movieType}"
    }

    data object MovieDetailsScreen : Screens(
        route = "movie_details"
    ){
        fun createRoute(title: String, id: Int) = "movie_details/${title}/${id}"
    }

}