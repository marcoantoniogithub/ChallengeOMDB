package com.example.desafiomobile.core.database.repository

import com.example.desafiomobile.core.database.dao.FilmFavoriteDAO
import com.example.desafiomobile.core.database.entity.FilmFavoriteEntity

class DatabaseDataSource(
    private val filmFavoriteDAO: FilmFavoriteDAO
) : FilmFavoriteRepository {
    override suspend fun insertFilm(film: FilmFavoriteEntity): Long {
        return filmFavoriteDAO.insert(film)
    }

    override suspend fun deleteFilm(id: String) {
        filmFavoriteDAO.delete(id)
    }

    override suspend fun getAllFilms(): List<FilmFavoriteEntity> {
        return filmFavoriteDAO.getAll()
    }

    override suspend fun existFilm(id: String): FilmFavoriteEntity {
        return filmFavoriteDAO.exist(id)
    }
}