package com.example.desafiomobile.ui.favoriteMoviesList.presentation

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.desafiomobile.util.base.BaseFragment
import com.example.desafiomobile.util.base.BaseViewModel
import com.example.desafiomobile.R
import com.example.desafiomobile.business.model.SimpleFilm
import com.example.desafiomobile.data.db.AppDatabase
import com.example.desafiomobile.data.db.dao.FilmFavoriteDAO
import com.example.desafiomobile.data.db.repository.DatabaseDataSource
import com.example.desafiomobile.data.db.repository.FilmFavoriteRepository
import com.example.desafiomobile.databinding.FragmentFavoriteFilmBinding
import com.example.desafiomobile.util.adapter.ListAdapter

class ListFavoriteMovieFragment : BaseFragment<FragmentFavoriteFilmBinding>() {

    private val viewModel: ListFavoriteMovieViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: FilmFavoriteDAO =
                    AppDatabase.getInstance(requireContext()).FilmFavoriteDAO

                val repository: FilmFavoriteRepository = DatabaseDataSource(subscriberDAO)
                return ListFavoriteMovieViewModel(repository) as T
            }
        }
    }
    private lateinit var filmsAdapter: ListAdapter

    override fun getLayout(): Int = R.layout.fragment_favorite_film

    override fun getViewModel(): BaseViewModel = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        val items = mutableListOf<SimpleFilm>()
        filmsAdapter = ListAdapter(items) { filmModel: SimpleFilm, i: Int ->
            navigationForDetails(filmModel, i)
        }.also {
            filmsAdapter = it
        }

        binding.listFilmFavoriteRecycleview.adapter = filmsAdapter

        binding.sum.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_favorite_film_to_list_film)
        }
    }

    private fun navigationForDetails(
        model: SimpleFilm,
        position: Int
    ) {
        val action = ListFavoriteMovieFragmentDirections.actionFavoriteFilmToDetailsFilm(model.id)
        view?.findNavController()?.navigate(action)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFilms()
    }

    override fun observers() {
        viewModel.films.observe(viewLifecycleOwner) {
            var newList: MutableList<SimpleFilm> = mutableListOf()
            for(film in it){
                newList.add(SimpleFilm(film.title!!, film.year!!, film.imdbID!!, film.poster!!))
            }
            filmsAdapter.updateList(newList)
        }
    }
}