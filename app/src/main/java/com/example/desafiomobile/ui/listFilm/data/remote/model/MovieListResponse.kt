package com.example.desafiomobile.ui.listFilm.data.remote.model

import com.example.desafiomobile.business.model.SimpleFilm
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("Search") val search: List<MovieResponse>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)