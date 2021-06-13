package com.example.desafiomobile.ui.favoriteFilm

import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.room.Room
import br.com.aaf.libraryCore.base.BaseFragment
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.R
import com.example.desafiomobile.business.dataBase.AppDatabase
import com.example.desafiomobile.business.model.SimpleFilm
import com.example.desafiomobile.databinding.FragmentFavoriteFilmBinding
import com.example.desafiomobile.ui.favoriteFilm.viewModel.FavoriteFilmViewModel
import com.example.desafiomobile.util.adapter.ListAdapter

class FavoriteFilmFragment : BaseFragment<FragmentFavoriteFilmBinding>() {

    private val viewModel: FavoriteFilmViewModel = FavoriteFilmViewModel()
    private lateinit var filmsAdapter: ListAdapter

    override fun getLayout(): Int = R.layout.fragment_favorite_film

    override fun getViewModel(): BaseViewModel = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "Film"
        ).build()

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
            filmsAdapter.updateList(it.search)
        }
    }
}