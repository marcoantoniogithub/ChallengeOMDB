package com.example.desafiomobile.ui.searchedMoviesList.domain.usecase

import com.example.desafiomobile.ui.searchedMoviesList.domain.model.MovieListState
import io.reactivex.Single

interface MovieListUseCase {
    fun loadMovies(searchText: String, firstPage: Boolean): Single<MovieListState>
}