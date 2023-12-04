package com.asif.tmdb.api

import com.asif.tmdb.data.cast.MovieCastDetails
import com.asif.tmdb.data.movieDetails.MovieDetailsResponse
import com.asif.tmdb.data.movieList.MovieListResponse
import com.asif.tmdb.data.trailers.TrailerListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {

    @GET("movie/{type}?")
    suspend fun getAllMoviesPaginated(
        @Path("type") type: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieListResponse


    @GET("movie/popular?language?language=en-US&page=1")
    suspend fun getPopularList(): MovieListResponse

    @GET("movie/top_rated?language?language=en-US&page=1")
    suspend fun getTopRatedList(): MovieListResponse

    @GET("movie/upcoming?language?language=en-US&page=1")
    suspend fun getUpcomingList(): MovieListResponse

    @GET("movie/now_playing?language?language=en-US&page=1")
    suspend fun getNowPlayingList(): MovieListResponse

    @GET("movie/{movie_id}?")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieCastDetails

    @GET("movie/{movie_id}?")
    suspend fun getTrailers(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): TrailerListResponse

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse
}