package com.example.desafiomobile.ui.detailsFilm.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.business.model.FilmDetailsDTO
import com.example.desafiomobile.business.repository.OmdbApi
import com.example.desafiomobile.data.db.entity.FilmFavoriteEntity
import com.example.desafiomobile.data.db.repository.FilmFavoriteRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsFilmViewModel(
    private val repository: FilmFavoriteRepository
) : BaseViewModel(), LifecycleObserver {

    var dto: MutableLiveData<FilmDetailsDTO> = MutableLiveData()
    var id: String = ""
    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getDetailsFilm()
        getFilm()
    }

    fun getDetailsFilm() {
        val retrofitClient = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(OmdbApi::class.java)

        println(endpoint)
        endpoint.getFilmForId(id).enqueue(object : Callback<FilmDetailsDTO> {
            override fun onResponse(
                call: Call<FilmDetailsDTO>,
                response: Response<FilmDetailsDTO>
            ) {
                dto.postValue(response.body())
            }

            override fun onFailure(call: Call<FilmDetailsDTO>, t: Throwable) {
                println(t)
            }
        })
    }

    fun getFilm() = viewModelScope.launch {
        try {
            val response = repository.existFilm(id)
            response?.let {
                favorite.postValue(true)
            }. also { favorite.postValue(false) }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }

    fun delete() = viewModelScope.launch {
        try {
            val response = repository.deleteFilm(id)
            response?.let {
                favorite.postValue(false)
            }. also { favorite.postValue(true) }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }

    fun add() = viewModelScope.launch {
        try {
            val response = repository.insertFilm(
                FilmFavoriteEntity(
                     title = null,
                     year = null,
                     rated = null,
                     released = null,
                     runtime = null,
                     genre = null,
                     director = null,
                     writer = null,
                     actors = null,
                     plot = null,
                     language = null,
                     country = null,
                     awards = null,
                     poster = null,
                     metascore = null,
                     imdbRating = null,
                     imdbVotes = null,
                     imdbID = null,
                     type = null,
                     dvd = null,
                     boxOffice = null,
                     production = null,
                     website = null,
                     response = null
                )
            )
            response?.let {
                favorite.postValue(true)
            }. also { favorite.postValue(false) }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }
}