package com.example.desafiomobile.ui.listFilm.presentation

import android.app.AlertDialog
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafiomobile.R
import com.example.desafiomobile.common.pagination
import com.example.desafiomobile.databinding.FragmentListFilmBinding
import com.example.desafiomobile.ui.listFilm.domain.model.Movie
import com.example.desafiomobile.ui.listFilm.presentation.di.MovieListPresentationFactory
import com.example.desafiomobile.util.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

class ListFilmFragment : BaseFragment<FragmentListFilmBinding>() {

    private val viewModel: ListFilmViewModel =
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
        val action = ListFilmFragmentDirections.actionListFilmToDetailsFilm(model.id)
        view?.findNavController()?.navigate(action)
    }

    override fun observers() {
        viewModel.action.observe(viewLifecycleOwner) {
            when (it) {
                ListFilmViewModel.ViewAction.CloseKeyboard -> showToast("Fechar teclado")
                ListFilmViewModel.ViewAction.HideLoading -> showToast("Remover loading")
                ListFilmViewModel.ViewAction.ShowLoading -> showToast("Mostrar loading")
                ListFilmViewModel.ViewAction.ShowSnackBarError -> showSnackBar("Ops... Houve um erro!")
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ListFilmViewModel.ViewState.LoadMoviesSuccess -> filmsAdapter.clearAndAdd(it.movies)
                ListFilmViewModel.ViewState.LoadMoviesFailure -> showSnackBar("Erro ao carregar filmes")
                is ListFilmViewModel.ViewState.LoadMoreMoviesSuccess -> filmsAdapter.addMovies(it.movies)
                ListFilmViewModel.ViewState.LoadMoreMoviesFailure -> showSnackBar("Erro ao carregar novos filmes")
                ListFilmViewModel.ViewState.NoMoreMovies -> showSnackBar("Chegamos ao fim :)")
                ListFilmViewModel.ViewState.ShowEmptyState -> AlertDialog.Builder(requireContext())
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