package com.example.desafiomobile.ui.listFilm.domain.repository

import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import io.reactivex.Single

interface MovieListRepository {
    fun requestMovie(searchText: String, fistPage: Boolean): Single<List<Movie>>
}