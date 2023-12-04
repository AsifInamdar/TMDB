package com.asif.tmdb.data.trailers


import com.google.gson.annotations.SerializedName

data class TrailerListResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
)