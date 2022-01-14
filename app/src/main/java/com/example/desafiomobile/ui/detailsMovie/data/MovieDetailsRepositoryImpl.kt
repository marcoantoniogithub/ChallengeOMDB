package com.example.desafiomobile.ui.detailsMovie.data

import com.example.desafiomobile.ui.detailsMovie.data.local.MovieDetailsLocalDataSource
import com.example.desafiomobile.ui.detailsMovie.data.remote.MovieDetailsDataSource
import com.example.desafiomobile.ui.detailsMovie.data.remote.MovieDetailsRemoteMapper
import com.example.desafiomobile.ui.detailsMovie.data.remote.model.MovieDetailsResponse
import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import com.example.desafiomobile.ui.detailsMovie.domain.repository.MovieDetailsRepository
import io.reactivex.Single

class MovieDetailsRepositoryImpl(
    private val remote: MovieDetailsDataSource,
    private val local: MovieDetailsLocalDataSource
): MovieDetailsRepository {
    override fun getDetailsMovie(id: String): Single<MovieDetails> =
        remote.getDetailsMovie(id)

    override fun addMovie(id: String) {
        return
    }

    override fun getMovie(id: String) {
        return
    }

    override fun deleteMovie(id: String) {
        return
    }
}