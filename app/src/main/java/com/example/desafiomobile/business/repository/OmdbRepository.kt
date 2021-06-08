package com.example.desafiomobile.business.repository

import br.com.aaf.libraryCore.base.BaseRepository
import br.com.aaf.libraryCore.base.BaseSchedulerProvider
import br.com.aaf.libraryCore.base.SchedulerProvider
import com.example.desafiomobile.business.model.FilmDTO
import io.reactivex.Single

class OmdbRepository(
    private val scheduler: SchedulerProvider,
    private val api: OmdbApi
) : BaseRepository() {

    override fun getScheduler(): BaseSchedulerProvider {
        return scheduler
    }

    fun getAllFilm(title :String): Single<FilmDTO> {
        return api.getFilmForTitle(title)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }
}