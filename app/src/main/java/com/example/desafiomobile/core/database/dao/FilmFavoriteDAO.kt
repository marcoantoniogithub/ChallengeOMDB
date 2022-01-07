package com.example.desafiomobile.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.desafiomobile.core.database.entity.FilmFavoriteEntity

@Dao
interface FilmFavoriteDAO {

    @Query("SELECT * FROM Film")
    fun getAll(): List<FilmFavoriteEntity>

    @Insert
    suspend fun insert(subscriber: FilmFavoriteEntity): Long

    @Query("DELETE FROM Film WHERE imdbID = :id")
    fun delete(id: String)


    @Query("SELECT * FROM Film WHERE imdbID = :id")
    fun exist(id: String): FilmFavoriteEntity
}