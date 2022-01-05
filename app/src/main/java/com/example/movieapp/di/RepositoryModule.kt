package com.example.movieapp.di

import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(apiService: APIService):MovieRepository{
        return MovieRepository(apiService)
    }
}