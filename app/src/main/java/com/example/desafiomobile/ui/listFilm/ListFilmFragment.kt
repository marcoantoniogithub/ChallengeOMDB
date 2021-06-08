package com.example.desafiomobile.ui.listFilm

import androidx.navigation.findNavController
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.databinding.FragmentListFilmBinding
import com.example.desafiomobile.ui.listFilm.di.ListFilmModuleModule.LIST_FILM_SCOPE
import com.example.desafiomobile.ui.listFilm.di.ListFilmModuleModule.LIST_FILM_SCOPE_ID
import com.example.desafiomobile.ui.listFilm.viewModel.ListFilmViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class ListFilmFragment : BaseFragment<FragmentListFilmBinding>() {

    private val scope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        LIST_FILM_SCOPE_ID,
        named(LIST_FILM_SCOPE)
    )

    private val viewModel: ListFilmViewModel = scope.get()

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
        scope.close()
        super.onDestroy()
    }

    override fun observers() {
    }

}