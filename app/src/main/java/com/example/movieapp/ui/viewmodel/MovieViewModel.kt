package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.model.BaseMovie
import com.example.movieapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val repository: MovieRepository): ViewModel() {
    private val _movies = MutableLiveData<DataState<BaseMovie>>()
    val movie:LiveData<DataState<BaseMovie>> get() = _movies

    fun getMovies(pageNumber: Int){
        viewModelScope.launch {
            Timber.e("movieViewModel")
            repository.movieList(pageNumber).onEach {
                _movies.value = it
            }.launchIn(viewModelScope)
        }
    }
}