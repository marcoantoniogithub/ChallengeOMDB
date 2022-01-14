package com.example.desafiomobile.ui.searchedMoviesList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobile.R
import com.example.desafiomobile.ui.searchedMoviesList.domain.model.Movie
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val films: MutableList<Movie> = mutableListOf()

    fun clearAndAdd(movies: List<Movie>) {
        films.clear()
        films.addAll(movies)
        notifyDataSetChanged()
    }

    fun addMovies(movies: List<Movie>) {
        films.addAll(movies)
        notifyDataSetChanged() // TODO mudar para atualizar o range
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list, viewGroup, false)
        )
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val filmModel = films[position]
        viewHolder.binding(filmModel) { listener(filmModel) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.card_title)
        private val year: TextView = view.findViewById(R.id.card_year)
        private val img: ImageView = view.findViewById(R.id.card_img)
        private val card: MaterialCardView = view.findViewById(R.id.card)
        private var id: String = "0"

        fun binding(filmsItem: Movie, onItemClickListener: () -> Unit) {
            title.text =
                if (filmsItem.title.length < 13) filmsItem.title else filmsItem.title.substring(
                    0,
                    10
                ) + "..."
            year.text = filmsItem.year
            Picasso.get()
                .load(filmsItem.poster)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(img)
            id = filmsItem.id
            card.setOnClickListener {
                onItemClickListener.invoke()
            }
        }
    }
}

