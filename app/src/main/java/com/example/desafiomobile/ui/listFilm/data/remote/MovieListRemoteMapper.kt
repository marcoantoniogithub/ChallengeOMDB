package com.example.desafiomobile.ui.listFilm.data.remote

import com.example.desafiomobile.ui.listFilm.data.remote.model.MovieListResponse
import com.example.desafiomobile.ui.listFilm.domain.model.Movie

class MovieListRemoteMapper {

    fun map(response: MovieListResponse) = response.search.map {
        Movie(it.title, it.year, it.id, it.poster)
    }
}