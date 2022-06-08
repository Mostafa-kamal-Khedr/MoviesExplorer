package com.mostafaKamal.moviesexplorer.views.fragments.movietrend.trendingmovies

import com.mostafaKamal.moviesexplorer.datasource.data.Data
import com.mostafaKamal.moviesexplorer.datasource.repository.MovieRepository
import com.mostafaKamal.moviesexplorer.model.MovieModel
import com.mostafaKamal.moviesexplorer.model.Query
import com.mostafaKamal.moviesexplorer.views.BaseViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
) : BaseViewModel() {
    private var popularMovies: Data<MovieModel>? = null


    fun fetchPopularMovies(connectivityAvailable: Boolean): Data<MovieModel>? {
        if (popularMovies == null) {
            popularMovies =
                repository.observePagedMovieList(connectivityAvailable, this, Query.POPULAR_MOVIES)
        }
        return popularMovies
    }


    fun refresh() {
        launch {
            repository.refreshMovieList(Query.LATEST_MOVIES)
        }
    }
}