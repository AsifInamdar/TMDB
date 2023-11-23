package com.asif.tmdb.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.asif.tmdb.api.MovieAPIService
import com.asif.tmdb.utils.logD
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataRepository @Inject constructor(private val apiService: MovieAPIService) {

    fun getAllMoviesPaginated(movieType: String): Flow<PagingData<MovieListDetail>> {
        val title = getMovieTypeBasedOnTitle(movieType)
        logD("Repository", "title $title")
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20
            ),
            pagingSourceFactory = { MovieListPagingSource(apiService, title) }
        ).flow
    }

    suspend fun getPopularMovies(): MovieListResponse {
        return apiService.getPopularList()
    }

    suspend fun getTopRatedList(): MovieListResponse {
        return apiService.getTopRatedList()
    }

    suspend fun getUpcomingList(): MovieListResponse {
        return apiService.getUpcomingList()
    }

    suspend fun getNowPlayingList(): MovieListResponse {
        return apiService.getNowPlayingList()
    }

    private fun getMovieTypeBasedOnTitle(title: String): String {
        logD("getMovieTitle", "title ${title.lowercase()}")
        return when (title.lowercase()) {
            "popular" -> {
                "popular"
            }

            "top rated" -> {
                "top_rated"
            }

            else -> {
                "upcoming"
            }
        }
    }

}