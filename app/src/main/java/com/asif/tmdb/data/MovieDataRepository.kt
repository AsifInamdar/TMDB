package com.asif.tmdb.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.asif.tmdb.api.MovieAPIService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataRepository @Inject constructor(private val apiService: MovieAPIService) {

    fun getAllMoviesPaginated(): Flow<PagingData<MovieListDetail>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20
            ),
            pagingSourceFactory = { MovieListPagingSource(apiService) }
        ).flow
    }

    suspend fun getPopularMovies(): MovieListResponse {
       return apiService.getPopularList()
    }

}