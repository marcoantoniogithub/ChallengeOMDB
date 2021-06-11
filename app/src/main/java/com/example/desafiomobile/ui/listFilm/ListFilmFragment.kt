package com.example.desafiomobile.ui.listFilm

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.business.model.SimpleFilm
import com.example.desafiomobile.databinding.FragmentListFilmBinding
import com.example.desafiomobile.ui.detailsFilm.DetailsFilmFragmentDirections
import com.example.desafiomobile.ui.listFilm.adapter.ListAdapter
import com.example.desafiomobile.ui.listFilm.viewModel.ListFilmViewModel

class ListFilmFragment : BaseFragment<FragmentListFilmBinding>() {

    private val viewModel: ListFilmViewModel = ListFilmViewModel()
    private lateinit var filmsAdapter: ListAdapter

    override fun getLayout() = R.layout.fragment_list_film
    override fun getViewModel() = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        val items = mutableListOf<SimpleFilm>()
        filmsAdapter = ListAdapter(items) { filmModel: SimpleFilm, i: Int ->
            navigationForDetails(filmModel, i)
        }.also {
            filmsAdapter = it
        }

        binding.listaNotasRecycleview.adapter = filmsAdapter
    }

    private fun navigationForDetails(
        model: SimpleFilm,
        position: Int
    ) {
        val action = ListFilmFragmentDirections.actionFirstFragmentToSecondFragment(model.id)
        view?.findNavController()?.navigate(action)
    }

    override fun onResume() {
        super.onResume()
        filmsAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun observers() {
        viewModel.films.observe(viewLifecycleOwner) {
            filmsAdapter.updateList(it.search)
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

}