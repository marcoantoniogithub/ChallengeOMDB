package com.example.desafiomobile.ui.detailsMovie.data.remote

import com.example.desafiomobile.ui.detailsMovie.data.remote.model.MovieDetailsResponse
import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import io.reactivex.Single

interface MovieDetailsDataSource {

    fun getDetailsMovie(id: String): Single<MovieDetails>
}