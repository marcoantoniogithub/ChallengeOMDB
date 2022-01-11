package com.example.desafiomobile.ui.listFilm.data.di

import com.example.desafiomobile.ui.listFilm.data.MovieListRepositoryImpl
import com.example.desafiomobile.ui.listFilm.data.local.MovieListLocalDataSource
import com.example.desafiomobile.ui.listFilm.data.local.MovieListLocalDataSourceImpl
import com.example.desafiomobile.ui.listFilm.data.remote.MovieListRemoteDataSource
import com.example.desafiomobile.ui.listFilm.data.remote.MovieListRemoteDataSourceImpl
import com.example.desafiomobile.ui.listFilm.data.remote.MovieListRemoteMapper
import com.example.desafiomobile.ui.listFilm.data.remote.api.MovieListApi
import com.example.desafiomobile.ui.listFilm.domain.repository.MovieListRepository

object MovieListDataFactory {

    fun provideRepository(): MovieListRepository = MovieListRepositoryImpl(
        provideLocalDataSource(),
        provideRemoteDataSource()
    )

    private fun provideLocalDataSource(): MovieListLocalDataSource = MovieListLocalDataSourceImpl()

    private fun provideRemoteDataSource(): MovieListRemoteDataSource =
        MovieListRemoteDataSourceImpl(MovieListApi, MovieListRemoteMapper())
}