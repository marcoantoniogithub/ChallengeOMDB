package com.example.desafiomobile.ui.searchedMoviesList.data.local

import com.example.desafiomobile.ui.searchedMoviesList.domain.model.Movie
import io.reactivex.Single

class MovieListLocalDataSourceImpl : MovieListLocalDataSource {

    override fun requestFirstMovies(searchText: String): Single<List<Movie>> {
        // TODO buscar os dados locais com o DAO
        // Fazer o map do DTO para o objeto Movie da camada de dominio
        return Single.just(
            listOf(
                Movie("Filme salvo local", "2020", "22", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU"),
                Movie("Filme salvo local 2", "2021", "21", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU")
            )
        ) // isso é um mock
    }

    override fun saveFirstMovies(searchText: String, data: List<Movie>) {
        // TODO salvar filmes local
    }
}