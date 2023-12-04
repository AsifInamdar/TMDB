package com.asif.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.asif.tmdb.data.movieList.MovieDataRepository
import com.asif.tmdb.data.movieList.MovieListDetail
import com.asif.tmdb.utils.POSTER_IMAGE_BASE_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieDataRepository: MovieDataRepository
) : ViewModel() {

    private val movieQueryParams = MutableStateFlow("popular")

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieListPaginated = movieQueryParams.flatMapLatest {
        movieDataRepository.getAllMoviesPaginated(movieQueryParams.value)
    }.cachedIn(viewModelScope)

    private val _popularMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val popularMovieList: StateFlow<List<MovieListDetail>> = _popularMovieList

    private val _topRatedMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val topRatedMovieList: StateFlow<List<MovieListDetail>> = _topRatedMovieList

    private val _upcomingMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val upcomingMovieList: StateFlow<List<MovieListDetail>> = _upcomingMovieList

    private val _nowPlayingMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val nowPlayingMovieList: StateFlow<List<MovieListDetail>> = _nowPlayingMovieList

    fun setMovieListType(movieType: String) {
        movieQueryParams.value = movieType
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovieList.value = movieDataRepository.getPopularMovies().movieList
        }
    }

    fun getTopRatedList() {
        viewModelScope.launch {
            _topRatedMovieList.value = movieDataRepository.getTopRatedList().movieList
        }
    }

    fun getUpcomingList() {
        viewModelScope.launch {
            _upcomingMovieList.value = movieDataRepository.getUpcomingList().movieList
        }
    }

    fun getNowPlayingList() {
        viewModelScope.launch {
            _nowPlayingMovieList.value = movieDataRepository.getNowPlayingList().movieList
        }
    }

    fun getStaticMovieObject(): MovieListDetail {
        return MovieListDetail(
            adult = false,
            backdropPath = "$POSTER_IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
            genreIds = emptyList(),
            id = 0,
            originalLanguage = "EN",
            originalTitle = "Game Of Thrones",
            overview = "",
            popularity = 5.6,
            posterPath = "$POSTER_IMAGE_BASE_URL/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
            releaseDate = "",
            title = "Game Of Thrones",
            video = false,
            voteAverage = 1.3,
            voteCount = 10
        )
    }
}
