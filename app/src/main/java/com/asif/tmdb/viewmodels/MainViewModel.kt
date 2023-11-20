package com.asif.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.asif.tmdb.data.MovieDataRepository
import com.asif.tmdb.data.MovieListDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val IMAGE_BASE_URL = "https://www.themoviedb.org/t/p/w220_and_h330_face"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieDataRepository: MovieDataRepository
) : ViewModel() {

    val popularMovieListPaginated =
        movieDataRepository.getAllMoviesPaginated().cachedIn(viewModelScope)

    private val _popularMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val popularMovieList : StateFlow<List<MovieListDetail>> = _popularMovieList

    fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovieList.value = movieDataRepository.getPopularMovies().movieList
        }
    }


    fun getStaticMovieObject(): MovieListDetail{
        return MovieListDetail(adult = false,
            backdropPath = "$IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
            genreIds = emptyList(),
            id = 0,
            originalLanguage ="EN",
            originalTitle = "Game Of Thrones",
            overview = "",
            popularity = 5.6,
            posterPath = "$IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
            releaseDate = "",
            title = "Game Of Thrones",
            video = false,
            voteAverage = 1.3,
            voteCount = 10
        )
    }
}
