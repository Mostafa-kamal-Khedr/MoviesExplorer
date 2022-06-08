package com.mostafaKamal.moviesexplorer.model

import com.mostafaKamal.moviesexplorer.moviesexplorer.model.Dates

data class ResponseModel(
    val dates: Dates,
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)