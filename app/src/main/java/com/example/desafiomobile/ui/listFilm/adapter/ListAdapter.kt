package com.example.desafiomobile.ui.listFilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobile.R
import com.example.desafiomobile.business.model.FilmDTO
import com.example.desafiomobile.business.model.simpleFilm

class ListAdapter(private val films: FilmDTO) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = films.films.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.film = films.films.get(position)
        viewHolder.binding()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var film: simpleFilm? = null
        var title: TextView? = null
        var year: TextView? = null
        var id: TextView? = null
        var poster: TextView? = null

        init {
            title = view.findViewById(R.id.simple_text)
        }

        fun binding() {
            title?.text = film?.title
        }
    }
}

