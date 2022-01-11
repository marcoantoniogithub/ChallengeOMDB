package com.example.desafiomobile.ui.listFilm.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiomobile.common.PaginationListener
import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import com.example.desafiomobile.ui.listFilm.domain.model.MovieListState
import com.example.desafiomobile.ui.listFilm.domain.usecase.MovieListUseCase
import com.example.desafiomobile.ui.listFilm.presentation.model.MoviePresentationModel
import io.reactivex.disposables.CompositeDisposable

class ListFilmViewModel(
    private val useCase: MovieListUseCase,
    private val model: MoviePresentationModel
) : ViewModel(), PaginationListener {

    private val compositeDisposable = CompositeDisposable()
    val state = MutableLiveData<ViewState>()
    val action = MutableLiveData<ViewAction>() // TODO alterar para single live event
    private var isLoadMoreLocked = false

    val searchText
        get() = model.text

    override fun isPaginationLocked(): Boolean = isLoadMoreLocked

    override fun loadMore() = loadMovies(firstPage = false)

    fun loadMovies() = loadMovies(firstPage = true)

    private fun loadMovies(firstPage: Boolean) {
        if (isLoadMoreLocked)
            return

        val disposable = useCase.loadMovies(searchText,firstPage)
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .doOnError { onLoadMoviesFailure(firstPage) }
            .subscribe(this::onLoadMoviesSuccess)
        compositeDisposable.add(disposable)
    }

    private fun onLoadMoviesSuccess(movieListState: MovieListState) {
        state.value = when (movieListState) {
            MovieListState.EmptyState -> ViewState.ShowEmptyState
            is MovieListState.FirstMovies -> ViewState.LoadMoviesSuccess(movieListState.movies)
            is MovieListState.MoreMovies -> ViewState.LoadMoreMoviesSuccess(movieListState.movies)
            MovieListState.NoMoreMovies -> {
                isLoadMoreLocked = true
                ViewState.NoMoreMovies
            }
        }
    }

    private fun onLoadMoviesFailure(firstPage: Boolean) {
        state.value = if (firstPage) ViewState.LoadMoviesFailure
        else ViewState.LoadMoreMoviesFailure
    }

    private fun showLoading() {
        isLoadMoreLocked = true
        action.value = ViewAction.ShowLoading
    }

    private fun hideLoading() {
        isLoadMoreLocked = false
        action.value = ViewAction.HideLoading
    }

    sealed class ViewState {
        class LoadMoviesSuccess(val movies: List<Movie>) : ViewState()
        object LoadMoviesFailure : ViewState()
        class LoadMoreMoviesSuccess(val movies: List<Movie>) : ViewState()
        object LoadMoreMoviesFailure : ViewState()
        object NoMoreMovies : ViewState()
        object ShowEmptyState : ViewState()
    }

    sealed class ViewAction {
        object ShowLoading : ViewAction()
        object HideLoading : ViewAction()
        object CloseKeyboard : ViewAction()
        object ShowSnackBarError : ViewAction()
    }
}