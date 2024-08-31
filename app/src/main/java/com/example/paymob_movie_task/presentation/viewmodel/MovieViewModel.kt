package com.example.paymob_movie_task.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.domain.usecase.GetMoviesUseCase
import com.example.paymob_movie_task.domain.usecase.UpdateFavoriteStatusUseCase
import com.example.paymob_movie_task.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase

) : ViewModel() {
    private val _moviesState = MutableLiveData<UiState<List<Movie>>>()
    val moviesState: LiveData<UiState<List<Movie>>> = _moviesState
    fun getAllMovies(releaseYear: Int, sortBy: String) {
        _moviesState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val movies = getMoviesUseCase(releaseYear, sortBy)
                if (movies.isEmpty()){
                    _moviesState.value = UiState.Error("An error occurred")
                }
                else{
                    _moviesState.value = UiState.Success(movies)
                }
            } catch (e: Exception) {
                _moviesState.value = UiState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun addToFavorite(movie: Movie) {
        viewModelScope.launch {
            movie.isFavorite = !movie.isFavorite
            updateFavoriteStatusUseCase(movie)
        }
    }
}