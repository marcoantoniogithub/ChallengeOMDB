package com.example.desafiomobile.ui.listFilm.viewModel

import androidx.lifecycle.LifecycleObserver
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.business.model.FilmDTO
import com.example.desafiomobile.business.repository.OmdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFilmViewModel() : BaseViewModel(), LifecycleObserver {
//    repository: OmdbRepository

    init {
        val retrofitClient = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(OmdbApi::class.java)
        val callback = endpoint.getFilmForTitle("a")


        callback.enqueue(object : Callback<List<FilmDTO>> {
            override fun onFailure(call: Call<List<FilmDTO>>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<List<FilmDTO>>, response: Response<List<FilmDTO>>) {
                response.body()?.forEach {
                    println(it)
                }
            }
        })

//        repository.getAllFilm("a")
    }
}