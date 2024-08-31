package com.example.paymob_movie_task.presentation.di

import com.example.paymob_movie_task.domain.repository.MovieRepository
import com.example.paymob_movie_task.domain.usecase.GetFavoriteMoviesUseCase
import com.example.paymob_movie_task.domain.usecase.GetMoviesUseCase
import com.example.paymob_movie_task.domain.usecase.UpdateFavoriteStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(
        movieRepository: MovieRepository
    ): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateFavoriteStatusUseCase(
        movieRepository: MovieRepository
    ): UpdateFavoriteStatusUseCase {
        return UpdateFavoriteStatusUseCase(movieRepository)
    }

    @Provides
    fun provideGetFavoriteMoviesUseCase(
        movieRepository: MovieRepository
    ): GetFavoriteMoviesUseCase {
        return GetFavoriteMoviesUseCase(movieRepository)
    }
}