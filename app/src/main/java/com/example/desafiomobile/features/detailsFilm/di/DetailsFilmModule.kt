package com.example.desafiomobile.features.detailsFilm.di

import com.example.desafiomobile.core.database.dao.FilmFavoriteDAO
import com.example.desafiomobile.core.database.repository.DatabaseDataSource
import com.example.desafiomobile.core.database.repository.FilmFavoriteRepository
import com.example.desafiomobile.features.detailsFilm.presentation.DetailsFilmViewModel
import org.koin.dsl.module

object DetailsFilmModule {
    val instance = module {

        fun provideDao(dataBase: FilmFavoriteDAO): FilmFavoriteDAO{
            return dataBase
        }
        single<FilmFavoriteDAO> { provideDao(get()) }
        single<FilmFavoriteRepository> { DatabaseDataSource(filmFavoriteDAO = get()) }
        single<DetailsFilmViewModel>{ DetailsFilmViewModel( repository = get()) }
    }
}