package com.example.desafiomobile.ui.detailsMovie.data.remote

import com.example.desafiomobile.ui.detailsMovie.data.remote.model.MovieDetailsResponse
import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieDetails
import com.example.desafiomobile.ui.detailsMovie.domain.model.MovieRating

class MovieDetailsRemoteMapper {

    fun map(it: MovieDetailsResponse): MovieDetails {
        val rating: List<MovieRating> = it.ratings.map {
            MovieRating(it.source, it.value)
        }
        return MovieDetails(
            it.title,
            it.year,
            it.rated,
            it.released,
            it.runtime,
            it.genre,
            it.director,
            it.writer,
            it.actors,
            it.plot,
            it.language,
            it.country,
            it.awards,
            it.poster,
            it.metascore,
            it.imdbRating,
            it.imdbVotes,
            it.imdbID,
            it.type,
            it.dvd,
            it.boxOffice,
            it.production,
            it.website,
            it.response,
            rating
        )
    }
}