package com.example.desafiomobile.data.db.repository

import androidx.lifecycle.LiveData
import com.example.desafiomobile.data.db.dao.FilmFavoriteDAO
import com.example.desafiomobile.data.db.entity.FilmFavoriteEntity

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

    override suspend fun existFilm(id: String): LiveData<FilmFavoriteEntity> {
        return filmFavoriteDAO.exist(id)
    }
}