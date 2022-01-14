package com.example.desafiomobile.ui.detailsMovie.data.di

import com.example.desafiomobile.ui.detailsMovie.data.MovieDetailsRepositoryImpl
import com.example.desafiomobile.ui.detailsMovie.data.local.MovieDetailsLocalDataSource
import com.example.desafiomobile.ui.detailsMovie.data.local.MovieDetailsLocalDataSourceImpl
import com.example.desafiomobile.ui.detailsMovie.data.remote.MovieDetailsDataSource
import com.example.desafiomobile.ui.detailsMovie.data.remote.MovieDetailsDataSourceImpl
import com.example.desafiomobile.ui.detailsMovie.data.remote.MovieDetailsRemoteMapper
import com.example.desafiomobile.ui.detailsMovie.data.remote.api.MovieDetailsApi
import com.example.desafiomobile.ui.detailsMovie.domain.repository.MovieDetailsRepository

object MovieDetailsDataFactory {

    fun provideMovieDetailsRepository(): MovieDetailsRepository = MovieDetailsRepositoryImpl(
        remote = provideMovieDetailsDataSource(),
        local = provideMovieDetailsLocalDataSource()
    )

    private fun provideMovieDetailsDataSource(): MovieDetailsDataSource =
        MovieDetailsDataSourceImpl(
            mapper = provideMovieDetailsRemoteMapper(),
            api = provideMovieDetailsApi()
        )

    private fun provideMovieDetailsLocalDataSource(): MovieDetailsLocalDataSource =
        MovieDetailsLocalDataSourceImpl()

    private fun provideMovieDetailsRemoteMapper(): MovieDetailsRemoteMapper =
        MovieDetailsRemoteMapper()

    private fun provideMovieDetailsApi(): MovieDetailsApi =
        MovieDetailsApi
}