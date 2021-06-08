package com.example.desafiomobile.ui.detailsFilm.di

import com.example.desafiomobile.ui.detailsFilm.viewModel.DetailsFilmViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DetailsFilmModule {

    const val DETAILS_FILM_SCOPE = "DETAILS_FILM_SCOPE"
    const val DETAILS_FILM_SCOPE_ID = "DETAILS_FILM_SCOPE_ID"

    val detailsFilmModule = module {
        scope(named(DETAILS_FILM_SCOPE)) {
            viewModel {
                DetailsFilmViewModel()
            }
//            scoped {
//                SignInRepository(
//                    scheduler = get(),
//                    api = get(named(APIEnum.AAF_API_NO_SESSION)),
//                    sharedPreferencesManager = get()
//                )
//            }
//            scoped<SigInApi>(named(APIEnum.AAF_API_NO_SESSION)) {
//                get<Retrofit>(ApiNoSessionRequest).newBuilder().build().create(SigInApi::class.java)
//            }
        }
    }
}