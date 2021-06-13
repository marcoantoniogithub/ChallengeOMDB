package com.example.desafiomobile.business.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.desafiomobile.business.model.FilmFavoriteEntity
import com.example.desafiomobile.business.repository.FilmFavoriteDAO

@Database(entities = [FilmFavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun FilmFavoriteDAO(): FilmFavoriteDAO
}