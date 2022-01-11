package com.example.desafiomobile.ui.listFilm.domain.usecase

import com.example.desafiomobile.ui.listFilm.domain.model.MovieListState
import io.reactivex.Single

interface MovieListUseCase {
    fun loadMovies(searchText: String, firstPage: Boolean): Single<MovieListState>
}