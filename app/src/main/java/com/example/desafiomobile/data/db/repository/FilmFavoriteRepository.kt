package com.example.desafiomobile.data.db.repository

import androidx.lifecycle.LiveData
import com.example.desafiomobile.data.db.entity.FilmFavoriteEntity

interface FilmFavoriteRepository {

    suspend fun insertFilm(film: FilmFavoriteEntity): Long
    suspend fun deleteFilm(id: String)
    suspend fun getAllFilms(): List<FilmFavoriteEntity>?
    suspend fun existFilm(id: String): FilmFavoriteEntity
}