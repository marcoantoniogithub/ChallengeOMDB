package com.example.desafiomobile.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.desafiomobile.data.db.dao.FilmFavoriteDAO
import com.example.desafiomobile.data.db.entity.FilmFavoriteEntity

@Database(entities = [FilmFavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val FilmFavoriteDAO: FilmFavoriteDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).allowMainThreadQueries().build()

                }
                return instance
            }
        }
    }
}