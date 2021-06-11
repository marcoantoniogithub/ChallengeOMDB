package com.example.desafiomobile.ui.listFilm.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.business.model.FilmDTO
import com.example.desafiomobile.business.repository.OmdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFilmViewModel() : BaseViewModel(), LifecycleObserver {

    var films: MutableLiveData<FilmDTO> = MutableLiveData()

    init {
    }

    fun getAllFilms() {
        val retrofitClient = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(OmdbApi::class.java)
        endpoint.getFilmForTitle("Sta", 1).enqueue(object : Callback<FilmDTO> {
            override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
                films.postValue(response.body())
            }

            override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
                println(t)
            }
        })
    }
}