package com.example.desafiomobile.ui.listFilm.data.local.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: String, // PrimaryKey
    val title: String,
    val year: String,
    val poster: String
)