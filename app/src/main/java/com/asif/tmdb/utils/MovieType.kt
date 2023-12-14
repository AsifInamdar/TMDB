package com.asif.tmdb.utils

enum class MovieType(val displayName: String, val apiValue: String) {
    POPULAR("Popular","popular"),
    TOP_RATED("Top Rated","top_rated"),
    UPCOMING("Upcoming", "upcoming")
}