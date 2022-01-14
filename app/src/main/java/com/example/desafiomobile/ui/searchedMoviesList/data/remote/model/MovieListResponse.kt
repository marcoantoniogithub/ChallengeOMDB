package com.example.desafiomobile.ui.searchedMoviesList.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("Search") val search: List<MovieResponse>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)