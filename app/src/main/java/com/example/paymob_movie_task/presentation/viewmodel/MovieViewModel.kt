package com.example.paymob_movie_task.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>?>()
    val movies: MutableLiveData<List<Movie>?> = _movies

    fun getAllMovies(releaseYear: Int, sortBy: String) {
        viewModelScope.launch {
            _movies.value = getMoviesUseCase(releaseYear, sortBy)
        }
    }
}