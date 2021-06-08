package com.example.desafiomobile.business.repository

import com.example.desafiomobile.business.model.FilmDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface OmdbApi {

    @GET("t={title}")
    fun getFilmForTitle(@Path("title") title: String): Single<FilmDTO>

}