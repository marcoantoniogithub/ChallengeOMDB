package com.example.desafiomobile.ui.listFilm

import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import br.com.aaf.libraryCore.base.BaseFragment
import com.example.desafiomobile.R
import com.example.desafiomobile.databinding.FragmentListFilmBinding
import com.example.desafiomobile.ui.listFilm.adapter.ListAdapter
import com.example.desafiomobile.ui.listFilm.adapter.listener.OnItemClickListener
import com.example.desafiomobile.ui.listFilm.viewModel.ListFilmViewModel

class ListFilmFragment : BaseFragment<FragmentListFilmBinding>() {

    private val viewModel: ListFilmViewModel = ListFilmViewModel()

    private var adapter: ListAdapter? = null
    var films: RecyclerView? = null

    override fun getLayout() = R.layout.fragment_list_film
    override fun getViewModel() = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        films = binding.listaNotasRecycleview

//        binding.buttonFirst.setOnClickListener {
//            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun observers() {
        viewModel.films.observe(viewLifecycleOwner) {
            adapter = ListAdapter(it)
            films?.setAdapter(adapter)
    //            adapter.setOnItemClickListener(object : OnItemClickListener() {
    //                fun onItemClick(nota: Nota?, posicao: Int) {
    //                    vaiParaFormularioNotaActivityAltera(nota, posicao)
    //                }
    //            })
        }
    }

}