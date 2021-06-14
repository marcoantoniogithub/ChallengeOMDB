package com.example.desafiomobile.ui.detailsFilm.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
}