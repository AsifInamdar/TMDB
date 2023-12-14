package com.asif.tmdb

import com.asif.tmdb.api.MovieAPIService
import com.asif.tmdb.data.movieList.MovieDataRepository
import com.asif.tmdb.data.movieList.MovieListResponse
import com.asif.tmdb.utils.MovieType
import com.asif.tmdb.utils.getStaticMovieObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class MovieDataRepositoryTest {
    private lateinit var movieDataRepository: MovieDataRepository
    private lateinit var apiService: MovieAPIService
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        apiService = mock()
        movieDataRepository = MovieDataRepository(apiService)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getNowPlayingList returns valid movie list`() = runTest {
        // Arrange
        val fakeMovieListResponse = mockMovies()
        whenever(apiService.getNowPlayingList()).thenReturn(fakeMovieListResponse)

        // Act
        val result = movieDataRepository.getNowPlayingList()

        // Assert
        assertEquals(fakeMovieListResponse, result)
    }

    @Test
    fun `getPopularMovies returns MovieListResponse with empty list`() = runTest {
        // Arrange
        val emptyMovieListResponse = MovieListResponse(
            page = 1,
            totalResults = 100,
            totalPages = 10,
            movieList = emptyList()
        )
        whenever(apiService.getPopularList()).thenReturn(emptyMovieListResponse)

        // Act
        val result = movieDataRepository.getPopularMovies()

        // Assert
        assertEquals(emptyMovieListResponse, result)
        assertTrue(result.movieList.isEmpty())
    }

    @Test
    fun `getPopularMovies handles exception and returns empty MovieListResponse`() = runTest {
        // Arrange
        whenever(apiService.getPopularList()).thenThrow(RuntimeException("Network error"))

        // Act
        val result = movieDataRepository.getPopularMovies()

        // Assert
        assertTrue(result.movieList.isEmpty())
    }


    private fun mockMovies(): MovieListResponse {
        return MovieListResponse(
            page = 1,
            movieList = listOf(
                getStaticMovieObject(),
                getStaticMovieObject(),
                getStaticMovieObject()
            ),
            totalResults = 100,
            totalPages = 10
        )
    }

    @Test
    fun `getMovieTypeBasedOnTitle returns correct movie type for valid title`() {
        val title = MovieType.TOP_RATED.displayName

        val result = movieDataRepository.getMovieTypeBasedOnTitle(title)

        assertEquals(MovieType.TOP_RATED, result)
    }

    @Test
    fun `getMovieTypeBasedOnTitle returns default Popular value for invalid title`() {
        val title = "Trending"

        val result = movieDataRepository.getMovieTypeBasedOnTitle(title)

        assertTrue(result == MovieType.POPULAR)
    }

    @Test
    fun `getMovieTypeBasedOnTitle does not return popular for a valid title`() {
        val title = MovieType.TOP_RATED.displayName

        val result = movieDataRepository.getMovieTypeBasedOnTitle(title)

        assertNotEquals(MovieType.POPULAR, result)
    }
}