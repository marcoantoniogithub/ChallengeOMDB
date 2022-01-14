package com.example.desafiomobile.ui.detailsMovie.presentation.di

import com.example.desafiomobile.ui.detailsMovie.data.di.MovieDetailsDataFactory
import com.example.desafiomobile.ui.detailsMovie.domain.repository.MovieDetailsRepository
import com.example.desafiomobile.ui.detailsMovie.domain.usecase.MovieDetailsUseCase
import com.example.desafiomobile.ui.detailsMovie.domain.usecase.MovieDetailsUseCaseImpl
import com.example.desafiomobile.ui.detailsMovie.presentation.DetailsMovieViewModel

object MovieDetailsPresentationFactory {

    fun provideDetailsMovieViewModel(): DetailsMovieViewModel = DetailsMovieViewModel(
        useCase = provideUseCase()
    )

    fun provideUseCase(): MovieDetailsUseCase = MovieDetailsUseCaseImpl(
        repository = MovieDetailsDataFactory.provideMovieDetailsRepository()
    )
}