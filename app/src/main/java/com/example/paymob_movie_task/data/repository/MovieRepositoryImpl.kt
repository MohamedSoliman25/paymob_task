package com.example.paymob_movie_task.data.repository

import android.util.Log
import com.example.paymob_movie_task.data.remote.MovieApi
import com.example.paymob_movie_task.domain.model.Movie
import com.example.paymob_movie_task.domain.repository.MovieRepository
import com.example.paymob_movie_task.utils.MyUtils
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    private val TAG = "MovieRepositoryImpl"
    override suspend fun getMovies(releaseYear: Int, sortBy: String): List<Movie> {
        Log.d(TAG, "knkfnfk: "+ movieApi.getMovies(MyUtils.API_KEY, releaseYear, sortBy))
        return movieApi.getMovies(MyUtils.API_KEY, releaseYear, sortBy).movies
    }

}