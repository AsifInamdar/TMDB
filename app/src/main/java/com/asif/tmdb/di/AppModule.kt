package com.asif.tmdb.di

import com.asif.tmdb.api.MovieAPIService
import com.asif.tmdb.data.movieList.MovieDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideMovieDataRepository(apiService: MovieAPIService): MovieDataRepository =
        MovieDataRepository(apiService)

}