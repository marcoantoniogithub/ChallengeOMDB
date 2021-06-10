package com.example.desafiomobile.ui.detailsFilm.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.business.model.FilmDTO
import com.example.desafiomobile.business.model.FilmDetailsDTO
import com.example.desafiomobile.business.repository.OmdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsFilmViewModel : BaseViewModel(), LifecycleObserver {

    var dto: MutableLiveData<FilmDetailsDTO> = MutableLiveData()

    init {
        val retrofitClient = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(OmdbApi::class.java)
        endpoint.getFilmForId("a").enqueue(object: Callback<FilmDetailsDTO> {
            override fun onResponse(call: Call<FilmDetailsDTO>, response: Response<FilmDetailsDTO>) {
                dto.postValue(response.body())
            }
            override fun onFailure(call: Call<FilmDetailsDTO>, t: Throwable) {
                println(t)
            }
        })
    }
}