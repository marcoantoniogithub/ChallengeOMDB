package com.example.desafiomobile.ui.detailsMovie.presentation

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
import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import com.example.desafiomobile.ui.detailsMovie.domain.usecase.MovieDetailsUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsMovieViewModel(
    private val useCase: MovieDetailsUseCase
) : BaseViewModel(), LifecycleObserver {

    var dto: MutableLiveData<MovieDetails> = MutableLiveData()
    var favorite: MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getDetailsMovie(id : String) {
        val disposable = useCase.getDetailsMovie(id)
//            .doOnSubscribe { showLoading() }
//            .doFinally { hideLoading() }
//            .doOnError { onLoadMoviesFailure(firstPage) }
            .subscribe(this::onLoadMovie)
        compositeDisposable.add(disposable)
//        val retrofitClient = Retrofit.Builder()
//            .baseUrl("http://www.omdbapi.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val endpoint = retrofitClient.create(OmdbApi::class.java)
//
//        endpoint.getFilmForId(id).enqueue(object : Callback<FilmDetailsDTO> {
//            override fun onResponse(
//                call: Call<FilmDetailsDTO>,
//                response: Response<FilmDetailsDTO>
//            ) {
//                dto.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<FilmDetailsDTO>, t: Throwable) {
//                println(t)
//            }
//        })
    }

    fun onLoadMovie(movieDetails:MovieDetails){
        dto.postValue(movieDetails)
    }

    fun getMovie(id: String) = viewModelScope.launch {
        useCase.getMovie(id)
//        try {
//            val response = repository.existFilm(id)
//            if(response == null){
//                favorite.postValue(false)
//            }
//            else {
//                favorite.postValue(true)
//            }
//        } catch (ex: Exception) {
//            Log.e(TAG, ex.toString())
//        }
    }

    fun deleteMovie(id: String) = viewModelScope.launch {
        useCase.deleteMovie(id)
//        try {
//            val response = repository.deleteFilm(id)
//            if(response == null){
//                favorite.postValue(true)
//            }
//            else {
//                favorite.postValue(false)
//            }
//        } catch (ex: Exception) {
//            Log.e(TAG, ex.toString())
//        }
    }
//
    fun addMovie(id: String) = viewModelScope.launch {
        useCase.addMovie(id)
//        try {
//            val response = repository.insertFilm(
//                FilmFavoriteEntity(
//                     title = dto.value?.title,
//                     year = dto.value?.year,
//                     rated = dto.value?.rated,
//                     released = dto.value?.released,
//                     runtime = dto.value?.runtime,
//                     genre = dto.value?.genre,
//                     director = dto.value?.director,
//                     writer = dto.value?.writer,
//                     actors = dto.value?.actors,
//                     plot = dto.value?.plot,
//                     language = dto.value?.language,
//                     country = dto.value?.country,
//                     awards = dto.value?.awards,
//                     poster = dto.value?.poster,
//                     metascore = dto.value?.metascore,
//                     imdbRating = dto.value?.imdbRating,
//                     imdbVotes = dto.value?.imdbVotes,
//                     imdbID = dto.value?.imdbID,
//                     type = dto.value?.type,
//                     dvd = dto.value?.dvd,
//                     boxOffice = dto.value?.boxOffice,
//                     production = dto.value?.production,
//                     website = dto.value?.website,
//                     response = dto.value?.response
//                )
//            )
//            if(response == null){
//                favorite.postValue(false)
//            }
//            else {
//                favorite.postValue(true)
//            }
//        } catch (ex: Exception) {
//            Log.e(TAG, ex.toString())
//        }
    }
}