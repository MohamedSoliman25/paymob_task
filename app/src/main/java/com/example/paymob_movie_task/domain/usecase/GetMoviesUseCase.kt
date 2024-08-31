package com.example.paymob_movie_task.domain.usecase

import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.domain.repository.MovieRepository

class GetMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(releaseYear: Int, sortBy: String): List<Movie> {
        return repository.getMovies(releaseYear, sortBy)
    }
}