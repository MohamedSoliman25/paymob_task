package com.example.paymob_movie_task.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paymob_movie_task.domain.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String,
    val originalLanguage: String,
    val isFavorite: Boolean
)

fun MovieEntity.toDomainModel(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        overview = overview,
        originalLanguage = originalLanguage,
        isFavorite = isFavorite
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        overview = overview,
        originalLanguage = originalLanguage,
        isFavorite = isFavorite
    )
}

