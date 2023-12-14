package com.asif.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asif.tmdb.data.movieDetails.MovieDetailsWrapped
import com.asif.tmdb.data.movieList.MovieDataRepository
import com.asif.tmdb.utils.formatRevenue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDataRepository: MovieDataRepository
) : ViewModel() {

    private val _movieDetails =
        Channel<MovieDetailsWrapped>()
    val movieDetails = _movieDetails.receiveAsFlow()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDataRepository.getMovieDetails(movieId)
                .map { movie ->
                    MovieDetailsWrapped(
                        adult = movie.adult,
                        backdropPath = movie.backdropPath,
                        belongsToCollection = movie.belongsToCollection,
                        budget = movie.budget,
                        genres = movie.genres.joinToString(","),
                        homepage = movie.homepage,
                        id = movie.id,
                        imdbId = movie.imdbId,
                        originalLanguage = movie.originalLanguage.uppercase(Locale.ENGLISH),
                        originalTitle = movie.originalTitle,
                        overview = movie.overview,
                        popularity = movie.popularity,
                        posterPath = movie.posterPath,
                        productionCompanies = movie.productionCompanies,
                        productionCountries = movie.productionCountries,
                        releaseDate = movie.releaseDate,
                        revenue = formatRevenue(movie.revenue).toString(),
                        runtime = movie.runtime,
                        spokenLanguages = movie.spokenLanguages,
                        status = movie.status,
                        tagline = movie.tagline,
                        title = movie.title,
                        video = movie.video,
                        voteAverage = String.format("%.1f", movie.voteAverage),
                        voteCount = movie.voteCount,
                        genreNReleaseDate = movie.releaseDate.take(4)
                                + ", "
                                + movie.productionCountries.map { it.iso31661 }
                            .joinToString(", ")
                                + ", "
                                + movie.genres.map { it.name }
                            .joinToString(", ")
                    )
                }
                .collect { details ->
                    _movieDetails.send(details)
                }
        }
    }
}