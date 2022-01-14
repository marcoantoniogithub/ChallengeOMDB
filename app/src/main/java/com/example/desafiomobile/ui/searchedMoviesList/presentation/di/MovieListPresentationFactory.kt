package com.example.desafiomobile.ui.searchedMoviesList.presentation.di

import com.example.desafiomobile.ui.searchedMoviesList.data.di.MovieListDataFactory
import com.example.desafiomobile.ui.searchedMoviesList.domain.usecase.MovieListUseCaseImpl
import com.example.desafiomobile.ui.searchedMoviesList.presentation.ListMovieViewModel
import com.example.desafiomobile.ui.searchedMoviesList.presentation.model.MoviePresentationModel

object MovieListPresentationFactory {

    fun provideListFilmViewModel(): ListMovieViewModel = ListMovieViewModel(
        provideUseCase(),
        MoviePresentationModel("")
    )

    private fun provideUseCase() = MovieListUseCaseImpl(MovieListDataFactory.provideRepository())
}