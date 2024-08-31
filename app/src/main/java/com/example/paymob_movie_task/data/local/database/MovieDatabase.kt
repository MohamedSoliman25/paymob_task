package com.example.paymob_movie_task.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paymob_movie_task.data.local.dao.MovieDao
import com.example.paymob_movie_task.data.local.entities.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}