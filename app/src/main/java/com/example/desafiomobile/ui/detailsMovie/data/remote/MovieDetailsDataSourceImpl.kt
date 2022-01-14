package com.example.desafiomobile.ui.detailsMovie.data.remote

import com.example.desafiomobile.ui.detailsMovie.data.remote.api.MovieDetailsApi
import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailsDataSourceImpl(
    private val api: MovieDetailsApi,
    private val mapper: MovieDetailsRemoteMapper

) : MovieDetailsDataSource{

    override fun getDetailsMovie(id: String): Single<MovieDetails> =
        api.getDetailsMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(mapper::map)
}