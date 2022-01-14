package com.example.desafiomobile.ui.detailsMovie.domain.usecase

import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import io.reactivex.Single

interface MovieDetailsUseCase {

    fun getDetailsMovie(id : String): Single<MovieDetails>
    fun addMovie(id: String)
    fun getMovie(id: String)
    fun deleteMovie(id: String)
}