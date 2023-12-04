package com.asif.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.asif.tmdb.data.movieList.MovieDataRepository
import com.asif.tmdb.data.movieList.MovieListDetail
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

}
