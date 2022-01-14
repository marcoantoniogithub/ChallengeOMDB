package com.example.desafiomobile.ui.searchedMoviesList.data.remote

import com.example.desafiomobile.ui.searchedMoviesList.domain.model.Movie
import io.reactivex.Single

interface MovieListRemoteDataSource {
    fun requestMovie(searchText: String, firstPage: Boolean): Single<List<Movie>>
}