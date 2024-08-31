package com.example.paymob_movie_task.domain.repository

import com.example.paymob_movie_task.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(releaseYear: Int, sortBy: String): List<Movie>
    suspend fun updateFavoriteStatus(movie: Movie)
    fun getFavoriteMovies(): Flow<List<Movie>>

}