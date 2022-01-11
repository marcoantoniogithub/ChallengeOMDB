package com.example.desafiomobile.ui.listFilm.data.remote.api

import com.example.desafiomobile.ui.listFilm.data.remote.model.MovieListResponse
import com.example.desafiomobile.ui.listFilm.data.remote.model.MovieResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object MovieListApi {

/*    @GET
    fun getFilmForTitle(
        @Query("s") title: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>*/

    // TODO Essa será a interface do retrofit, fiz assim para mockar a resposta

    fun getMovie(title: String, page: Int) = Single.just(
        MovieListResponse(
            search = listOf(
                MovieResponse("Gustavo 1", "2010", "1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 2", "2012", "2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                MovieResponse("Gustavo 3", "2014", "3", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU")
            ),
            totalResults = "10",
            response = ""
        )
    )
}