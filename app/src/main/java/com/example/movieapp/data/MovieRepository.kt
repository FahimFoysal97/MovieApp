package com.example.movieapp.data

import com.example.movieapp.data.model.BaseMovie
import com.example.movieapp.data.remote.APIService
import com.example.movieapp.data.remote.APIUrls
import com.example.movieapp.utils.DataState
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception
import java.net.URLEncoder
import javax.inject.Inject

class MovieRepository @Inject constructor(val apiService: APIService) {
    fun movieList(pageNumber: Int)= flow<DataState<BaseMovie>> {
        emit(DataState.Loading)
        try {
            val result = apiService.movieList(pageNumber)
            Timber.e("Repository: ${result.page}")
            emit(DataState.Success(result))
        }catch (exception:Exception){
            emit(DataState.Error(exception))
        }
    }
}