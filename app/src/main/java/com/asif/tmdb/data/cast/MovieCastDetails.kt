package com.asif.tmdb.data.cast


import com.google.gson.annotations.SerializedName

data class MovieCastDetails(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)