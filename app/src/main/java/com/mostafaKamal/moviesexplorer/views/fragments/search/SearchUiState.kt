package com.mostafaKamal.moviesexplorer.views.fragments.search

import com.movieapp.domain.model.HomeTypeModel
import com.movieapp.domain.model.Search

data class SearchUiState(
    val isLoading: Boolean = false,
    val search: List<Search> = emptyList(),
    val error: String = ""
)
