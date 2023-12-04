package com.asif.tmdb.di

import com.asif.tmdb.api.MovieAPIService
import com.asif.tmdb.utils.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): MovieAPIService {
        return retrofit.create(MovieAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        logging: HttpLoggingInterceptor,
        authInterceptor: Interceptor
    ): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideAuthToken(): Interceptor {
        val token =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTRhYmMzOTBhNWVmYzI5MWI3ZTlmOTFhM2Y2YzY0YyIsInN1YiI6IjY1MWU4YzBhYTA5N2RjMDBhZTczMjIxYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Me1EbTx7UnlatcNampCFhoL7clkMB8bBnDhBxgrBtfs"
        return Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()

            chain.proceed(newRequest)
        }
    }

}