package com.example.desafiomobile.ui.searchedMoviesList.domain.repository

import com.example.desafiomobile.ui.searchedMoviesList.domain.model.Movie
import io.reactivex.Single

interface MovieListRepository {
    fun requestMovie(searchText: String, fistPage: Boolean): Single<List<Movie>>
}