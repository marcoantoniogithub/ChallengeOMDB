package com.example.desafiomobile.ui.listFilm.presentation.di

import com.example.desafiomobile.ui.listFilm.data.di.MovieListDataFactory
import com.example.desafiomobile.ui.listFilm.domain.usecase.MovieListUseCaseImpl
import com.example.desafiomobile.ui.listFilm.presentation.ListFilmViewModel
import com.example.desafiomobile.ui.listFilm.presentation.model.MoviePresentationModel

object MovieListPresentationFactory {

    fun provideListFilmViewModel(): ListFilmViewModel = ListFilmViewModel(
        provideUseCase(),
        MoviePresentationModel("")
    )

    private fun provideUseCase() = MovieListUseCaseImpl(MovieListDataFactory.provideRepository())
}