package com.example.desafiomobile.ui.detailsMovie.domain.usecase

import com.example.desafiomobile.data.db.repository.FilmFavoriteRepository
import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import com.example.desafiomobile.ui.detailsMovie.domain.repository.MovieDetailsRepository
import io.reactivex.Single

class MovieDetailsUseCaseImpl(
    private val repository: MovieDetailsRepository
): MovieDetailsUseCase {
    override fun getDetailsMovie(id: String): Single<MovieDetails> {
        return repository.getDetailsMovie(id)
    }

    override fun addMovie(id: String) {
        repository
    }

    override fun getMovie(id: String) {
        repository
    }

    override fun deleteMovie(id: String) {
        repository
    }
}