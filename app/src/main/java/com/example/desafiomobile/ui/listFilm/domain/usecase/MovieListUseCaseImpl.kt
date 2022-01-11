package com.example.desafiomobile.ui.listFilm.domain.usecase

import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import com.example.desafiomobile.ui.listFilm.domain.model.MovieListState
import com.example.desafiomobile.ui.listFilm.domain.repository.MovieListRepository
import io.reactivex.Single

class MovieListUseCaseImpl(private val repository: MovieListRepository) : MovieListUseCase {

    override fun loadMovies(searchText: String, firstPage: Boolean): Single<MovieListState> =
        repository.requestMovie(searchText,firstPage)
            .map { mapMoviesToState(it, firstPage) }

    /* TODO extrair isso para uma classe
    * Cenario 1 - primeira pagina e lista vazia, mostra empty state
    * Cenario 2 - lista vazia, ultima pagina
    * Cenario 3 - primeira pagina e lista com itens, carrega lista
    * Cenario 4 - carregamento de mais filmes de uma nova pagina
    * */
    private fun mapMoviesToState(list: List<Movie>?, firstPage: Boolean) =
        when {
            firstPage && list.isNullOrEmpty() -> MovieListState.EmptyState
            list.isNullOrEmpty() -> MovieListState.NoMoreMovies
            firstPage -> MovieListState.FirstMovies(list)
            else -> MovieListState.MoreMovies(list)
        }
}