package com.example.desafiomobile.ui.detailsMovie.data.remote.api

import com.example.desafiomobile.ui.detailsMovie.data.remote.model.MovieDetailsResponse
import com.example.desafiomobile.ui.detailsMovie.data.remote.model.MovieRatingResponse
import io.reactivex.Single

object MovieDetailsApi {

    fun getDetailsMovie(id: String) = Single.just(
        MovieDetailsResponse(
            title = "Filme salvo local 1",
            year = "2022",
            rated = "rated",
            released = "released",
            runtime = "160min",
            genre = "genre",
            director = "director",
            writer = "writer",
            actors = "actors",
            plot = "plot",
            language = "language",
            country = "country",
            awards = "awards",
            poster = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpZJjfNWQlDQsgzCeWcHiON-PXd8esd0IZuQUiCdF5ridOpyYrNLz_7d7mhkJPA2Gizl4&usqp=CAU",
            metascore = "metascore",
            imdbRating = "imdbRating",
            imdbVotes = "imdbVotes",
            imdbID = "imdbID",
            type = "type",
            dvd = "dvd",
            boxOffice = "boxOffice",
            production = "production",
            website = "website",
            response = "response",
            ratings = listOf(
                MovieRatingResponse(
                    source = "",
                    value = ""
                ), MovieRatingResponse(
                    source = "",
                    value = ""
                )
            )
        )
    )
}