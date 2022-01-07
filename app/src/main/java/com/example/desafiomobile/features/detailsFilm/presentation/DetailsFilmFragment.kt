package com.example.desafiomobile.features.detailsFilm.presentation

import android.graphics.Color
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.desafiomobile.R
import com.example.desafiomobile.databinding.FragmentDetailsFilmBinding
import com.example.desafiomobile.core.util.BaseFragment
import com.example.desafiomobile.core.util.BaseViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsFilmFragment : BaseFragment<FragmentDetailsFilmBinding>() {

    private val viewModel: DetailsFilmViewModel by inject { parametersOf(this) }

    val args: DetailsFilmFragmentArgs by navArgs()

    override fun getLayout() = R.layout.fragment_details_film
    override fun getViewModel(): BaseViewModel = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)
        viewModel.getDetailsFilm(args.id)
        viewModel.getFilm(args.id)

        binding.buttonBack.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }

        binding.btnStar.setOnClickListener {
            if (viewModel.favorite.value!!) {
                viewModel.delete(args.id)
            } else {
                viewModel.add(args.id)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun observers() {

        viewModel.dto.observe(viewLifecycleOwner) {
            Picasso.get()
                .load(it.poster)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(binding.imgId);

            binding.title.text = it.title
            binding.year.text = it.year
            binding.released.text = it.released
            binding.genre.text = it.genre
            binding.rating.text = if (it.ratings.size > 0) it.ratings[0].value else "Not Found"
            binding.plot.text = it.plot
        }

        viewModel.favorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnStar.setBackgroundColor(Color.parseColor("#F9FF13"))
            } else {
                binding.btnStar.setBackgroundColor(Color.parseColor("#AAAAAA"))
            }
        }
    }
}