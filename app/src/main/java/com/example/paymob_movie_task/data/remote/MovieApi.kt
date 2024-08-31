package com.example.paymob_movie_task.data.remote

import com.example.paymob_movie_task.domain.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: Int,
        @Query("sort_by") sortBy: String
    ): MovieResponse
}