package com.example.paymob_movie_task.presentation.di

import com.example.paymob_movie_task.data.local.dao.MovieDao
import com.example.paymob_movie_task.data.remote.MovieApi
import com.example.paymob_movie_task.data.repository.MovieRepositoryImpl
import com.example.paymob_movie_task.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi,
        dao: MovieDao
    ): MovieRepository {
        return MovieRepositoryImpl(api,dao)
    }
}
