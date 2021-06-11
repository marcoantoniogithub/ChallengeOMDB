package com.example.desafiomobile.ui.listFilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobile.R
import com.example.desafiomobile.business.model.SimpleFilm
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class ListAdapter(
    private var films: MutableList<SimpleFilm>,
    private val listener: ((SimpleFilm, position: Int) -> Unit)
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list, viewGroup, false)
        )
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val filmModel = films[position]
        viewHolder.binding(filmModel) { listener(filmModel, position) }
    }

    fun updateList(list: List<SimpleFilm>) {
        films = list.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.card_title)
        private val year: TextView = view.findViewById(R.id.card_year)
        private val img: ImageView = view.findViewById(R.id.card_img)
        private val card: MaterialCardView = view.findViewById(R.id.card)
        private var id: String = "0"

        fun binding(filmsItem: SimpleFilm, onItemClickListener: () -> Unit) {
            title.text = if (filmsItem.title.length < 13) filmsItem.title else filmsItem.title.substring(0, 10) + "..."
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

