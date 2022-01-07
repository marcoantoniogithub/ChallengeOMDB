package com.example.desafiomobile.features.favoriteFilm.presentation

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.desafiomobile.core.util.BaseFragment
import com.example.desafiomobile.core.util.BaseViewModel
import com.example.desafiomobile.R
import com.example.desafiomobile.business.model.SimpleFilm
import com.example.desafiomobile.core.database.AppDatabase
import com.example.desafiomobile.core.database.dao.FilmFavoriteDAO
import com.example.desafiomobile.core.database.repository.DatabaseDataSource
import com.example.desafiomobile.core.database.repository.FilmFavoriteRepository
import com.example.desafiomobile.databinding.FragmentFavoriteFilmBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FavoriteFilmFragment : BaseFragment<FragmentFavoriteFilmBinding>() {

    private val viewModel: FavoriteFilmViewModel by inject { parametersOf(this) }

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
        val action = FavoriteFilmFragmentDirections.actionFavoriteFilmToDetailsFilm(model.id)
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