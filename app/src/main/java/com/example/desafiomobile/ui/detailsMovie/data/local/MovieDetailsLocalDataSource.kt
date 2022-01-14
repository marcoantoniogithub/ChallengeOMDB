package com.example.desafiomobile.ui.detailsMovie.data.local

interface MovieDetailsLocalDataSource {

    fun addMovie(id: String)
    fun getMovie(id: String)
    fun deleteMovie(id: String)
}