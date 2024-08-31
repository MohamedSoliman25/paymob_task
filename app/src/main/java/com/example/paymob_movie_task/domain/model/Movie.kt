package com.example.paymob_movie_task.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String,
    val originalLanguage: String,
    var isFavorite: Boolean = false
)
