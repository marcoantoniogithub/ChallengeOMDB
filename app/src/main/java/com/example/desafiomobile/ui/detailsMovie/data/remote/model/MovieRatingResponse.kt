package com.example.desafiomobile.ui.detailsMovie.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieRatingResponse(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
)
