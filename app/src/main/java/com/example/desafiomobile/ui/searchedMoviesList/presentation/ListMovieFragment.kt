package com.example.desafiomobile.ui.searchedMoviesList.presentation

import android.app.AlertDialog
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafiomobile.R
import com.example.desafiomobile.common.pagination
import com.example.desafiomobile.databinding.FragmentListFilmBinding
import com.example.desafiomobile.ui.searchedMoviesList.domain.model.Movie
import com.example.desafiomobile.ui.searchedMoviesList.presentation.di.MovieListPresentationFactory
import com.example.desafiomobile.util.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

class ListMovieFragment : BaseFragment<FragmentListFilmBinding>() {

    private val viewModel: ListMovieViewModel =
        MovieListPresentationFactory.provideListFilmViewModel() // TODO usar o ViewModelProviders depois
    private lateinit var filmsAdapter: MovieAdapter

    override fun getLayout() = R.layout.fragment_list_film
    override fun getViewModel() = TODO("remover esse método")

    override fun initBinding() {
        binding.viewModel = viewModel

        filmsAdapter = MovieAdapter { movie: Movie->
            navigationForDetails(movie)
        }

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.listFilmRecycleview.apply {
            this.layoutManager = layoutManager
            pagination(layoutManager, viewModel)
            adapter = filmsAdapter
        }

        binding.buttonBack.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }
    }

    private fun navigationForDetails(model: Movie) {
        val action = ListMovieFragmentDirections.actionListFilmToDetailsFilm(model.id)
        view?.findNavController()?.navigate(action)
    }

    override fun observers() {
        viewModel.action.observe(viewLifecycleOwner) {
            when (it) {
                ListMovieViewModel.ViewAction.CloseKeyboard -> showToast("Fechar teclado")
                ListMovieViewModel.ViewAction.HideLoading -> showToast("Remover loading")
                ListMovieViewModel.ViewAction.ShowLoading -> showToast("Mostrar loading")
                ListMovieViewModel.ViewAction.ShowSnackBarError -> showSnackBar("Ops... Houve um erro!")
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ListMovieViewModel.ViewState.LoadMoviesSuccess -> filmsAdapter.clearAndAdd(it.movies)
                ListMovieViewModel.ViewState.LoadMoviesFailure -> showSnackBar("Erro ao carregar filmes")
                is ListMovieViewModel.ViewState.LoadMoreMoviesSuccess -> filmsAdapter.addMovies(it.movies)
                ListMovieViewModel.ViewState.LoadMoreMoviesFailure -> showSnackBar("Erro ao carregar novos filmes")
                ListMovieViewModel.ViewState.NoMoreMovies -> showSnackBar("Chegamos ao fim :)")
                ListMovieViewModel.ViewState.ShowEmptyState -> AlertDialog.Builder(requireContext())
                    .setMessage("Empty state").show()
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(),text,Toast.LENGTH_SHORT).show()
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }
}