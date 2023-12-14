package com.asif.tmdb.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.asif.tmdb.data.movieDetails.MovieDetailsResponse
import com.asif.tmdb.data.movieList.MovieListDetail
import com.asif.tmdb.data.movieList.MovieListResponse
import java.util.Locale

fun logE(tag: String, message: String) {
    Log.e(tag, message)
}

fun logD(tag: String, message: String) {
    Log.d(tag, message)
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun getStaticMovieObject(): MovieListDetail {
    return MovieListDetail(
        adult = false,
        backdropPath = "$POSTER_IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
        genreIds = emptyList(),
        id = 0,
        originalLanguage = "EN",
        originalTitle = "Game Of Thrones",
        overview = "",
        popularity = 5.6,
        posterPath = "$POSTER_IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
        releaseDate = "",
        title = "Game Of Thrones",
        video = false,
        voteAverage = 1.3,
        voteCount = 10
    )
}

fun getStaticMovieListResponse(): MovieListResponse {
    return MovieListResponse(
        movieList = emptyList(),
        page = 1,
        totalPages = 10,
        totalResults = 100
    )
}

fun getStaticMovieDetailsObject(): MovieDetailsResponse {
    return MovieDetailsResponse(
        adult = false,
        backdropPath = "$POSTER_IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
        id = 0,
        originalLanguage = "EN",
        originalTitle = "Game Of Thrones",
        overview = "",
        popularity = 5.6,
        posterPath = "$POSTER_IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
        releaseDate = "",
        title = "Game Of Thrones",
        video = false,
        voteAverage = 1.3,
        voteCount = 10,
        belongsToCollection = "",
        budget = 1000000,
        genres = emptyList(),
        homepage = "",
        imdbId = "123",
        productionCompanies = emptyList(),
        productionCountries = emptyList(),
        revenue = 235000000,
        runtime = 15465,
        spokenLanguages = emptyList(),
        status = "Released",
        tagline = "Test"
    )
}

fun formatRevenue(revenue: Int?): String? {
    if (revenue == null) return null

    val currencyFormat = java.text.NumberFormat.getCurrencyInstance(Locale("en", "US"))
    return currencyFormat.format(revenue.toString().substringAfter("$").toDouble())
}