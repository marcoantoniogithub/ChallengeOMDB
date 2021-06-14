package com.example.desafiomobile.ui.detailsFilm.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.desafiomobile.util.base.BaseViewModel
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
    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    fun getDetailsFilm(id : String) {
        val retrofitClient = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(OmdbApi::class.java)

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

    fun getFilm(id: String) = viewModelScope.launch {
        try {
            val response = repository.existFilm(id)
            if(response == null){
                favorite.postValue(false)
            }
            else {
                favorite.postValue(true)
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }

    fun delete(id: String) = viewModelScope.launch {
        try {
            val response = repository.deleteFilm(id)
            if(response == null){
                favorite.postValue(true)
            }
            else {
                favorite.postValue(false)
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }

    fun add(id: String) = viewModelScope.launch {
        try {
            val response = repository.insertFilm(
                FilmFavoriteEntity(
                     title = dto.value?.title,
                     year = dto.value?.year,
                     rated = dto.value?.rated,
                     released = dto.value?.released,
                     runtime = dto.value?.runtime,
                     genre = dto.value?.genre,
                     director = dto.value?.director,
                     writer = dto.value?.writer,
                     actors = dto.value?.actors,
                     plot = dto.value?.plot,
                     language = dto.value?.language,
                     country = dto.value?.country,
                     awards = dto.value?.awards,
                     poster = dto.value?.poster,
                     metascore = dto.value?.metascore,
                     imdbRating = dto.value?.imdbRating,
                     imdbVotes = dto.value?.imdbVotes,
                     imdbID = dto.value?.imdbID,
                     type = dto.value?.type,
                     dvd = dto.value?.dvd,
                     boxOffice = dto.value?.boxOffice,
                     production = dto.value?.production,
                     website = dto.value?.website,
                     response = dto.value?.response
                )
            )
            if(response == null){
                favorite.postValue(false)
            }
            else {
                favorite.postValue(true)
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }
}