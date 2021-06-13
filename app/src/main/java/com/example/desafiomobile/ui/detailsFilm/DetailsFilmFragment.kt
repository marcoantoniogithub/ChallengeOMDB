package com.example.desafiomobile.ui.detailsFilm

import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.databinding.FragmentDetailsFilmBinding
import com.example.desafiomobile.ui.detailsFilm.viewModel.DetailsFilmViewModel
import com.squareup.picasso.Picasso

class DetailsFilmFragment : BaseFragment<FragmentDetailsFilmBinding>() {

    val args: DetailsFilmFragmentArgs by navArgs()

    private val viewModel: DetailsFilmViewModel = DetailsFilmViewModel()

    override fun getLayout() = R.layout.fragment_details_film
    override fun getViewModel() = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        binding.buttonBack.setOnClickListener {
//            view?.findNavController()?.backStack(R.id.action_details_film_to_list_film)
        }

        viewModel.getDetailsFilm(args.id)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun observers() {
        viewModel.dto.observe( viewLifecycleOwner) {
            Picasso.get()
                .load(it.poster)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(binding.imgId);

            binding.title.text = it.title
            binding.year.text = it.year
            binding.released.text = it.released
            binding.genre.text = it.genre
            binding.rating.text = if(it.ratings.size>0)it.ratings[0].value else "Not Found"
            binding.plot.text = it.plot
        }
    }
}