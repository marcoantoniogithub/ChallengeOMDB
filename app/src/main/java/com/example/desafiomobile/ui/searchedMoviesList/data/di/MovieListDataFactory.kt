package com.example.desafiomobile.ui.searchedMoviesList.data.di

import com.example.desafiomobile.ui.searchedMoviesList.data.MovieListRepositoryImpl
import com.example.desafiomobile.ui.searchedMoviesList.data.local.MovieListLocalDataSource
import com.example.desafiomobile.ui.searchedMoviesList.data.local.MovieListLocalDataSourceImpl
import com.example.desafiomobile.ui.searchedMoviesList.data.remote.MovieListRemoteDataSource
import com.example.desafiomobile.ui.searchedMoviesList.data.remote.MovieListRemoteDataSourceImpl
import com.example.desafiomobile.ui.searchedMoviesList.data.remote.MovieListRemoteMapper
import com.example.desafiomobile.ui.searchedMoviesList.data.remote.api.MovieListApi
import com.example.desafiomobile.ui.searchedMoviesList.domain.repository.MovieListRepository

object MovieListDataFactory {

    fun provideRepository(): MovieListRepository = MovieListRepositoryImpl(
        provideLocalDataSource(),
        provideRemoteDataSource()
    )

    private fun provideLocalDataSource(): MovieListLocalDataSource = MovieListLocalDataSourceImpl()

    private fun provideRemoteDataSource(): MovieListRemoteDataSource =
        MovieListRemoteDataSourceImpl(MovieListApi, MovieListRemoteMapper())
}