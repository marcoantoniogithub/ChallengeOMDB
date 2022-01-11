package com.example.desafiomobile.ui.listFilm.data.local

import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import io.reactivex.Single

class MovieListLocalDataSourceImpl : MovieListLocalDataSource {

    override fun requestFirstMovies(searchText: String): Single<List<Movie>> {
        // TODO buscar os dados locais com o DAO
        // Fazer o map do DTO para o objeto Movie da camada de dominio
        return Single.just(listOf(Movie("Filme salvo local", "2020", "22", ""))) // isso é um mock
    }

    override fun saveFirstMovies(searchText: String, data: List<Movie>) {
        // TODO salvar filmes local
    }
}