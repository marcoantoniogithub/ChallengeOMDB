package com.example.desafiomobile.business.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.desafiomobile.business.model.FilmFavoriteEntity

@Dao
interface FilmFavoriteDAO {

    @Query("SELECT * FROM films")
    fun getAll(): List<FilmFavoriteEntity>

    @Query("SELECT * FROM films WHERE imdbID = :imdbId LIMIT 1")
    fun findByImdbId(imdbId : String):FilmFavoriteEntity

    @Insert
    fun insertAll(vararg users: FilmFavoriteEntity)

    @Delete
    fun delete(user: FilmFavoriteEntity)
}