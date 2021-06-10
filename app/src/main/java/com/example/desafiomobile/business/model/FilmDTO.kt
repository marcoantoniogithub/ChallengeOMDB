package com.example.desafiomobile.business.model

import com.google.gson.annotations.SerializedName

data class FilmDTO(
    @SerializedName("Search") val films: List<simpleFilm>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)

data class simpleFilm(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Poster") val poster: String
)