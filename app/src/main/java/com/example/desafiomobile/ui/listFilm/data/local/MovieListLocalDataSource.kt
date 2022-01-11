package com.example.desafiomobile.ui.listFilm.data.local

import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import io.reactivex.Single

interface MovieListLocalDataSource {
    fun requestFirstMovies(searchText: String): Single<List<Movie>>
    fun saveFirstMovies(searchText: String, data: List<Movie>)
}