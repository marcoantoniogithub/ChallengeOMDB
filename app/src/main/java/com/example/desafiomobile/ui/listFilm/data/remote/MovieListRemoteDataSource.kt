package com.example.desafiomobile.ui.listFilm.data.remote

import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import io.reactivex.Single

interface MovieListRemoteDataSource {
    fun requestMovie(searchText: String, firstPage: Boolean): Single<List<Movie>>
}