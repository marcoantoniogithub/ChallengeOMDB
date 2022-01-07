package com.example.desafiomobile.features.listFilm.di

import com.example.desafiomobile.features.listFilm.presentation.ListFilmViewModel
import org.koin.dsl.module

object ListFilmModule {

    val instance = module {
        single<ListFilmViewModel> { ListFilmViewModel() }
    }
}