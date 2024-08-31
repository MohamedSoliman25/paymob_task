package com.example.paymob_movie_task.domain.usecase

import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.domain.repository.MovieRepository

class UpdateFavoriteStatusUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) {
        return repository.updateFavoriteStatus(movie)
    }
}