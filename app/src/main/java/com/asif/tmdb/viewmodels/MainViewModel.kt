package com.asif.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.asif.tmdb.data.movieList.MovieDataRepository
import com.asif.tmdb.data.movieList.MovieListDetail
import com.asif.tmdb.utils.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val movieQueryParams = MutableStateFlow(MovieType.POPULAR)

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieListPaginated = movieQueryParams.flatMapLatest { movieType ->
        movieDataRepository.getAllMoviesPaginated(movieType)
    }.cachedIn(viewModelScope)

    private val _popularMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val popularMovieList: StateFlow<List<MovieListDetail>> = _popularMovieList

    private val _topRatedMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val topRatedMovieList: StateFlow<List<MovieListDetail>> = _topRatedMovieList

    private val _upcomingMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val upcomingMovieList: StateFlow<List<MovieListDetail>> = _upcomingMovieList

    private val _nowPlayingMovieList = MutableStateFlow(emptyList<MovieListDetail>())
    val nowPlayingMovieList: StateFlow<List<MovieListDetail>> = _nowPlayingMovieList

    fun setMovieListType(movieType: MovieType) {
        movieQueryParams.value = movieType
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovieList.value = movieDataRepository.getPopularMovies().movieList
        }
    }

    fun getTopRatedList() {
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedMovieList.value = movieDataRepository.getTopRatedList().movieList
        }
    }

    fun getUpcomingList() {
        viewModelScope.launch(Dispatchers.IO) {
            _upcomingMovieList.value = movieDataRepository.getUpcomingList().movieList
        }
    }

    fun getNowPlayingList() {
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingMovieList.value = movieDataRepository.getNowPlayingList().movieList
        }
    }

}
