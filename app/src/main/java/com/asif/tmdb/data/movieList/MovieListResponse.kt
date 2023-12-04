package com.asif.tmdb.data.movieList

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieListDetail>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)