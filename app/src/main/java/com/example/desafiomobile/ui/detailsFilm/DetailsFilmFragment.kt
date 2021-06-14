package com.example.desafiomobile.ui.detailsFilm

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import br.com.aaf.libraryCore.base.BaseFragment
import br.com.aaf.libraryCore.base.BaseViewModel
import com.example.desafiomobile.R
import com.example.desafiomobile.data.db.AppDatabase
import com.example.desafiomobile.data.db.dao.FilmFavoriteDAO
import com.example.desafiomobile.data.db.repository.DatabaseDataSource
import com.example.desafiomobile.data.db.repository.FilmFavoriteRepository
import com.example.desafiomobile.databinding.FragmentDetailsFilmBinding
import com.example.desafiomobile.ui.detailsFilm.viewModel.DetailsFilmViewModel
import com.example.desafiomobile.ui.favoriteFilm.viewModel.FavoriteFilmViewModel
import com.squareup.picasso.Picasso

class DetailsFilmFragment : BaseFragment<FragmentDetailsFilmBinding>() {

    private val viewModel: DetailsFilmViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: FilmFavoriteDAO =
                    AppDatabase.getInstance(requireContext()).FilmFavoriteDAO

                val repository: FilmFavoriteRepository = DatabaseDataSource(subscriberDAO)
                return DetailsFilmViewModel(repository) as T
            }
        }
    }

    val args: DetailsFilmFragmentArgs by navArgs()

    override fun getLayout() = R.layout.fragment_details_film
    override fun getViewModel() : BaseViewModel = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        binding.buttonBack.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }

        binding.btnStar.setOnClickListener {
            if(viewModel.favorite.value!!){
                viewModel.delete()
            } else {
                viewModel.add()
            }
        }

        viewModel.id = args.id
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