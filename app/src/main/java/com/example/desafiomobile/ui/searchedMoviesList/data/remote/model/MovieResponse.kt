package com.example.desafiomobile.ui.searchedMoviesList.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Poster") val poster: String
)