package com.example.desafiomobile.ui.listFilm.viewModel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.example.desafiomobile.util.base.BaseViewModel
import com.example.desafiomobile.business.model.FilmDTO
import com.example.desafiomobile.business.model.SimpleFilm
import com.example.desafiomobile.business.repository.OmdbApi
import io.reactivex.Completable.merge
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFilmViewModel() : BaseViewModel(), LifecycleObserver {

    var films: MutableLiveData<FilmDTO> = MutableLiveData()
    var textTitle: MutableLiveData<String> = MutableLiveData()
    var msgError: MutableLiveData<String> = MutableLiveData()
    var closeKeyBoard: MutableLiveData<Boolean> = MutableLiveData()
    var page: Int = 0

    fun getAllFilms() {
        val retrofitClient = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofitClient.create(OmdbApi::class.java)
        endpoint.getFilmForTitle(textTitle.value ?: "", page).enqueue(object : Callback<FilmDTO> {
            override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
                if (response.body()?.search == null) {
                    msgError.postValue("Nâo foi Encontrado")
                } else {
                    if(page == 1){
                        films.postValue(response.body())
                    } else {
                        val list :  List<SimpleFilm> = films.value!!.search.plus(response.body()!!.search)
                        films.postValue(FilmDTO(search = list, response = films.value!!.response, totalResults = films.value!!.totalResults))
                    }
                }
            }

            override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
                println(t)
            }
        })
    }

    fun nextPage(){
        page++
        getAllFilms()
    }

    fun searchFilms(){
        page = 1
        closeKeyBoard.postValue(false)
        getAllFilms()
    }
}