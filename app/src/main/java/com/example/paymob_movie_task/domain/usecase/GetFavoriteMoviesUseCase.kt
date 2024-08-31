package com.example.paymob_movie_task.domain.usecase

import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(private val repository: MovieRepository) {
    fun invoke(): Flow<List<Movie>> {
        return repository.getFavoriteMovies()
    }
}