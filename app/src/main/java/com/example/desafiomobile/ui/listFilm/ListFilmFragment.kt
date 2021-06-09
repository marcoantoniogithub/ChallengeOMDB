package com.example.desafiomobile.ui.listFilm

import androidx.navigation.findNavController
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.databinding.FragmentListFilmBinding
import com.example.desafiomobile.ui.listFilm.viewModel.ListFilmViewModel

class ListFilmFragment : BaseFragment<FragmentListFilmBinding>() {

    private val viewModel: ListFilmViewModel = ListFilmViewModel()

    override fun getLayout() = R.layout.fragment_list_film
    override fun getViewModel() = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        binding.buttonFirst.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun observers() {
    }

}