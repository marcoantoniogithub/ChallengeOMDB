package com.example.desafiomobile.ui.detailsFilm

import androidx.lifecycle.observe
import androidx.navigation.findNavController
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.databinding.FragmentDetailsFilmBinding
import com.example.desafiomobile.ui.detailsFilm.viewModel.DetailsFilmViewModel
import com.squareup.picasso.Picasso

class DetailsFilmFragment : BaseFragment<FragmentDetailsFilmBinding>() {

    private val viewModel: DetailsFilmViewModel = DetailsFilmViewModel()

    override fun getLayout() = R.layout.fragment_details_film
    override fun getViewModel() = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        binding.buttonBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun observers() {
        viewModel.dto.observe( viewLifecycleOwner) {
            Picasso.get()
                .load(it.poster)
                .into(binding.imgId);

            binding.title.text = it.title
            binding.year.text = it.year
            binding.runtime.text = it.runtime
            binding.genre.text = it.genre
            binding.plot.text = it.plot
        }
    }
}