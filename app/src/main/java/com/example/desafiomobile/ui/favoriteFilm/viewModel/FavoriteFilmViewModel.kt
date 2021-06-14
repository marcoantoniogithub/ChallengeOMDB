package com.example.desafiomobile.ui.favoriteFilm.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.R
import com.example.desafiomobile.data.db.entity.FilmFavoriteEntity
import com.example.desafiomobile.data.db.repository.FilmFavoriteRepository
import kotlinx.coroutines.launch

class FavoriteFilmViewModel(
    private val repository: FilmFavoriteRepository
) : BaseViewModel(), LifecycleObserver {

    var films: MutableLiveData<List<FilmFavoriteEntity>> = MutableLiveData()

    init {
        getAllFilms()
    }

    fun getAllFilms() = viewModelScope.launch {
        try {
            val response = repository.getAllFilms()
            response?.let {
                films.postValue(it)
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }
}