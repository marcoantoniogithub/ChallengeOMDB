package com.example.desafiomobile.ui.listFilm

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.business.model.SimpleFilm
import com.example.desafiomobile.databinding.FragmentListFilmBinding
import com.example.desafiomobile.util.adapter.ListAdapter
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

        binding.listFilmRecycleview.adapter = filmsAdapter
    }

    private fun navigationForDetails(
        model: SimpleFilm,
        position: Int
    ) {
        val action = ListFilmFragmentDirections.actionListFilmToDetailsFilm(model.id)
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

        viewModel.msgError.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        viewModel.closeKeyBoard.observe(viewLifecycleOwner) {
            activity?.let {
                val imm: InputMethodManager =
                    it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                var view: View? = it.currentFocus
                if (view == null) {
                    view = View(activity)
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }
    }

}