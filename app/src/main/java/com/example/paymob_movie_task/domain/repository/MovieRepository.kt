package com.example.paymob_movie_task.domain.repository

import com.example.paymob_movie_task.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(releaseYear: Int, sortBy: String): List<Movie>

}