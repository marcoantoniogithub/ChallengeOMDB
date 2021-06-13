package com.example.desafiomobile.ui.favoriteFilm.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.business.model.FilmDTO

class FavoriteFilmViewModel : BaseViewModel(), LifecycleObserver {


    var films: MutableLiveData<FilmDTO> = MutableLiveData()

    init {

    }

    fun getAllFilms() {
        print("")

    }

}