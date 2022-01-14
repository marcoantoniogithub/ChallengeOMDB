package com.example.desafiomobile.ui.detailsMovie.domain.repository

import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import io.reactivex.Single

interface MovieDetailsRepository {
    fun getDetailsMovie(id: String): Single<MovieDetails>
    fun addMovie(id: String)
    fun getMovie(id: String)
    fun deleteMovie(id: String)
}