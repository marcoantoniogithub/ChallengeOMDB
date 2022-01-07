package com.example.desafiomobile.features.favoriteFilm.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.desafiomobile.core.util.BaseViewModel
import com.example.desafiomobile.core.database.entity.FilmFavoriteEntity
import com.example.desafiomobile.core.database.repository.FilmFavoriteRepository
import kotlinx.coroutines.launch

class FavoriteFilmViewModel(
    private val repository: FilmFavoriteRepository
) : BaseViewModel(), LifecycleObserver {

    var films: MutableLiveData<List<FilmFavoriteEntity>> = MutableLiveData()

    init {

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