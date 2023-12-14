package com.asif.tmdb.data.movieList

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.asif.tmdb.api.MovieAPIService
import com.asif.tmdb.data.movieDetails.MovieDetailsResponse
import com.asif.tmdb.utils.MovieType
import com.asif.tmdb.utils.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataRepository @Inject constructor(private val apiService: MovieAPIService) {

    fun getAllMoviesPaginated(movieType: MovieType): Flow<PagingData<MovieListDetail>> {
        val title = getMovieTypeBasedOnTitle(movieType.displayName).apiValue
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
        return try {
            apiService.getPopularList()
        } catch (e: Exception) {
            MovieListResponse(page = 0, movieList = emptyList(), totalPages = 0, totalResults = 0)
        }
    }

    suspend fun getTopRatedList(): MovieListResponse {
        return try {
            apiService.getTopRatedList()
        } catch (e: Exception) {
            MovieListResponse(page = 0, movieList = emptyList(), totalPages = 0, totalResults = 0)
        }
    }

    suspend fun getUpcomingList(): MovieListResponse {
        return try {
            apiService.getUpcomingList()
        } catch (e: Exception) {
            MovieListResponse(page = 0, movieList = emptyList(), totalPages = 0, totalResults = 0)
        }
    }

    suspend fun getNowPlayingList(): MovieListResponse {
        return try {
            apiService.getNowPlayingList()
        } catch (e: Exception) {
            MovieListResponse(page = 0, movieList = emptyList(), totalPages = 0, totalResults = 0)
        }
    }

    fun getMovieTypeBasedOnTitle(title: String): MovieType {
        return when (title) {
            MovieType.POPULAR.displayName -> MovieType.POPULAR
            MovieType.TOP_RATED.displayName -> MovieType.TOP_RATED
            MovieType.UPCOMING.displayName -> MovieType.UPCOMING
            else -> MovieType.POPULAR
        }
    }


    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetailsResponse> {
        return flow {
            emit(apiService.getMovieDetails(movieId))
        }.flowOn(Dispatchers.IO)
    }

}