package com.example.paymob_movie_task.data.repository

import android.util.Log
import com.example.paymob_movie_task.data.local.dao.MovieDao
import com.example.paymob_movie_task.data.local.entities.toDomainModel
import com.example.paymob_movie_task.data.local.entities.toEntity
import com.example.paymob_movie_task.data.remote.MovieApi
import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.domain.model.toDomainModel
import com.example.paymob_movie_task.domain.repository.MovieRepository
import com.example.paymob_movie_task.utils.MyUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) : MovieRepository {
    private val TAG = "MovieRepositoryImpl"
    override suspend fun getMovies(releaseYear: Int, sortBy: String): List<Movie> {
        val apiMovies = movieApi.getMovies(MyUtils.API_KEY, releaseYear, sortBy).movies.map { it.toDomainModel() }
        val favoriteMovies = movieDao.getFavoriteMovies().first().map { it.id }.toSet()

        return apiMovies.map { movie ->
            movie.copy(isFavorite = favoriteMovies.contains(movie.id))
        }
    }

    override suspend fun updateFavoriteStatus(movie: Movie) {
        val movieEntity = movie.toEntity()
        movieDao.insertMovie(movieEntity)
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieDao.getFavoriteMovies().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }
}