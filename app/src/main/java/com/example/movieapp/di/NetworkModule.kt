package com.example.movieapp.di

import com.example.movieapp.data.remote.APIService
import com.example.movieapp.data.remote.APIUrls
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(APIUrls.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): APIService{
        return retrofit.create(APIService::class.java)
    }
}