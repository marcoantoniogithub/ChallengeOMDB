package com.example.desafiomobile.ui.searchedMoviesList.data.remote

import com.example.desafiomobile.ui.searchedMoviesList.data.remote.api.MovieListApi
import com.example.desafiomobile.ui.searchedMoviesList.domain.model.Movie
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieListRemoteDataSourceImpl(
    private val movieListApi: MovieListApi,
    private val mapper: MovieListRemoteMapper
) : MovieListRemoteDataSource {

    private var currentPage = FIRST_PAGE

    private fun getPage(firstPage: Boolean) = if (firstPage) {
        currentPage = FIRST_PAGE
        FIRST_PAGE
    } else {
        ++currentPage
    }

    override fun requestMovie(searchText: String, firstPage: Boolean): Single<List<Movie>> =
        movieListApi.getMovie(searchText, getPage(firstPage))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(mapper::map)

    companion object {
        const val FIRST_PAGE = 1
    }
}