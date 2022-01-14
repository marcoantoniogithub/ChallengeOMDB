package com.example.desafiomobile.ui.searchedMoviesList.domain.model

sealed class MovieListState {
    object EmptyState : MovieListState()
    object NoMoreMovies : MovieListState()
    class FirstMovies(val movies: List<Movie>) : MovieListState()
    class MoreMovies(val movies: List<Movie>) : MovieListState()
}