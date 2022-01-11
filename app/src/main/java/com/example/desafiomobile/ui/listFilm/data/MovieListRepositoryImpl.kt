package com.example.desafiomobile.ui.listFilm.data

import com.example.desafiomobile.ui.listFilm.data.local.MovieListLocalDataSource
import com.example.desafiomobile.ui.listFilm.data.remote.MovieListRemoteDataSource
import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import com.example.desafiomobile.ui.listFilm.domain.repository.MovieListRepository
import io.reactivex.Single

class MovieListRepositoryImpl(
    val localDataSource: MovieListLocalDataSource,
    val remoteDataSource: MovieListRemoteDataSource
) : MovieListRepository {

    // Salvar a primeira pagina local
    override fun requestMovie(searchText: String, firstPage: Boolean): Single<List<Movie>> =
        remoteDataSource.requestMovie(searchText, firstPage)
            .onErrorResumeNext { localDataSource.requestFirstMovies(searchText) }
            .doOnSuccess { saveLocalData(firstPage, it, searchText) }

    private fun saveLocalData(firstPage: Boolean, data: List<Movie>, searchText: String) {
        if (firstPage.not())
            return

        localDataSource.saveFirstMovies(searchText, data)
    }
}