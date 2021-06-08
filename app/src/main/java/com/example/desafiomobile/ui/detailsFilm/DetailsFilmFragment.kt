package com.example.desafiomobile.ui.detailsFilm

import androidx.navigation.findNavController
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.databinding.FragmentDetailsFilmBinding
import com.example.desafiomobile.ui.detailsFilm.di.DetailsFilmModule.DETAILS_FILM_SCOPE
import com.example.desafiomobile.ui.detailsFilm.di.DetailsFilmModule.DETAILS_FILM_SCOPE_ID
import com.example.desafiomobile.ui.detailsFilm.viewModel.DetailsFilmViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class DetailsFilmFragment : BaseFragment<FragmentDetailsFilmBinding>() {

    private val scope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        DETAILS_FILM_SCOPE_ID,
        named(DETAILS_FILM_SCOPE)
    )

    private val viewModel: DetailsFilmViewModel = scope.get()

    override fun getLayout() = R.layout.fragment_details_film
    override fun getViewModel() = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        binding.buttonSecond.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

    override fun observers() {
    }
}