package com.example.desafiomobile

import android.app.Application
import com.example.desafiomobile.di.DataModule.dataModule
import com.example.desafiomobile.ui.detailsFilm.di.DetailsFilmModule.detailsFilmModule
import com.example.desafiomobile.ui.listFilm.di.ListFilmModuleModule.listFilmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    dataModule,
                    listFilmModule,
                    detailsFilmModule
                )
            )
        }
    }
}