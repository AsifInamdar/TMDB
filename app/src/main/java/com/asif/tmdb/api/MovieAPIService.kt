package com.asif.tmdb.api

import com.asif.tmdb.data.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {

    @GET("movie/popular?")
    suspend fun getAllMoviesPaginated(
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieListResponse


    @GET("movie/popular?language?language=en-US&page=1")
    suspend fun getPopularList(): MovieListResponse


}