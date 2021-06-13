package com.example.desafiomobile.ui.favoriteFilm

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import br.com.aaf.libraryCore.base.BaseFragment
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.R
import com.example.desafiomobile.business.model.SimpleFilm
import com.example.desafiomobile.data.db.AppDatabase
import com.example.desafiomobile.data.db.dao.FilmFavoriteDAO
import com.example.desafiomobile.data.db.repository.DatabaseDataSource
import com.example.desafiomobile.data.db.repository.FilmFavoriteRepository
import com.example.desafiomobile.databinding.FragmentFavoriteFilmBinding
import com.example.desafiomobile.ui.favoriteFilm.viewModel.FavoriteFilmViewModel
import com.example.desafiomobile.util.adapter.ListAdapter

class FavoriteFilmFragment : BaseFragment<FragmentFavoriteFilmBinding>() {

    private val viewModel: FavoriteFilmViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: FilmFavoriteDAO =
                    AppDatabase.getInstance(requireContext()).FilmFavoriteDAO

                val repository: FilmFavoriteRepository = DatabaseDataSource(subscriberDAO)
                return FavoriteFilmViewModel(repository) as T
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
    }

    private fun navigationForDetails(
        model: SimpleFilm,
        position: Int
    ) {
        val action = FavoriteFilmFragmentDirections.actionFavoriteFilmToDetailsFilm(model.id)
        view?.findNavController()?.navigate(action)
    }

    override fun onResume() {
        super.onResume()
        filmsAdapter.notifyDataSetChanged()
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