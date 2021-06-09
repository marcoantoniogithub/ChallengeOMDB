package com.example.desafiomobile.business.repository

import com.example.desafiomobile.business.model.FilmDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbApi {
        @GET("?apikey=6931e6f5&")
        fun getFilmForTitle(@Query("t") title: String): Call<List<FilmDTO>>
}