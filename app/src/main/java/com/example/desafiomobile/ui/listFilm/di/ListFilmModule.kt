package com.example.desafiomobile.ui.listFilm.di

import br.com.aaf.libraryCore.base.SchedulerProvider
import com.example.desafiomobile.business.repository.OmdbApi
import com.example.desafiomobile.business.repository.OmdbRepository
import com.example.desafiomobile.ui.listFilm.viewModel.ListFilmViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object ListFilmModuleModule {

    const val LIST_FILM_SCOPE = "LIST_FILM_SCOPE"
    const val LIST_FILM_SCOPE_ID = "LIST_FILM_SCOPE_ID"
    const val NAMED_API = "API_NO_SESSION"

    val listFilmModule = module {
        scope(named(LIST_FILM_SCOPE)) {
            viewModel {
                ListFilmViewModel(
                    repository = get()
                )
            }
            scoped {
                OmdbRepository(
                    scheduler= get(),
                    api = get(named(NAMED_API))
                )
            }
            scoped<OmdbApi>(named(NAMED_API)) {
                 get<Retrofit>().newBuilder().build().create(OmdbApi::class.java)
            }
        }
    }
}