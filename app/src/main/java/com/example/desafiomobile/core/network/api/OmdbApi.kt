package com.example.desafiomobile.core.network.api

import com.example.desafiomobile.business.model.FilmDTO
import com.example.desafiomobile.business.model.FilmDetailsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbApi {
        @GET("?apikey=6931e6f5")
        fun getFilmForId(@Query("i") id: String): Call<FilmDetailsDTO>

        @GET("?apikey=6931e6f5")
        fun getFilmForTitle(@Query("s") title: String, @Query("page") page: Int): Call<FilmDTO>
}