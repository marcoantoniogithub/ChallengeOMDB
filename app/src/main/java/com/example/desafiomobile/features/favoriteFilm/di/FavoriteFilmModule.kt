package com.example.desafiomobile.features.favoriteFilm.di

import com.example.desafiomobile.core.database.dao.FilmFavoriteDAO
import com.example.desafiomobile.core.database.repository.DatabaseDataSource
import com.example.desafiomobile.core.database.repository.FilmFavoriteRepository
import com.example.desafiomobile.features.favoriteFilm.presentation.FavoriteFilmViewModel
import org.koin.dsl.module

object FavoriteFilmModule {

    val instance = module {

        fun provideDao(dataBase: FilmFavoriteDAO): FilmFavoriteDAO{
            return dataBase
        }
        single<FilmFavoriteDAO> { provideDao(get()) }
        single<FilmFavoriteRepository> { DatabaseDataSource(filmFavoriteDAO = get()) }
        single { FavoriteFilmViewModel(repository = get()) }
    }
}