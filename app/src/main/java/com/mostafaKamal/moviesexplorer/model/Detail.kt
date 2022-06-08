package com.mostafaKamal.moviesexplorer.domain.model

import com.mostafaKamal.moviesexplorer.model.MovieModel


data class Detail(
    val title: String?,
    val image: String?,
    val banner: String?,
    val description: String?,
    val imdb: String?,
    val genres: List<MovieModel>,
    val runtime:String?,
    val cast:List<MovieModel>?,
)
