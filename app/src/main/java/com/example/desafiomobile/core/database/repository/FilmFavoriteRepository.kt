package com.example.desafiomobile.core.database.repository

import com.example.desafiomobile.core.database.entity.FilmFavoriteEntity

interface FilmFavoriteRepository {

    suspend fun insertFilm(film: FilmFavoriteEntity): Long
    suspend fun deleteFilm(id: String)
    suspend fun getAllFilms(): List<FilmFavoriteEntity>?
    suspend fun existFilm(id: String): FilmFavoriteEntity
}