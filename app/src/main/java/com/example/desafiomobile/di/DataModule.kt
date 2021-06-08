package com.example.desafiomobile.di

import br.com.aaf.libraryCore.base.SchedulerProvider
import org.koin.dsl.module

object DataModule {
    val dataModule = module {
        single {
            SchedulerProvider()
        }
    }
}