package com.example.desafiomobile.ui.listFilm.adapter.listener

import com.example.desafiomobile.business.model.FilmDTO

interface OnItemClickListener {

    fun onItemClick(nota: FilmDTO?, posicao: Int)
}