package com.asif.tmdb.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asif.tmdb.api.MovieAPIService

private const val STARTING_PAGE_INDEX = 1

class MovieListPagingSource
    (private val service: MovieAPIService) :
    PagingSource<Int, MovieListDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListDetail> {

        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getAllMoviesPaginated(page, "en-US")
            val movies = response.movieList
            LoadResult.Page(
                data = movies,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieListDetail>): Int? {
        return state.anchorPosition
    }
}