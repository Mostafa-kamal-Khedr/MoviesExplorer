package com.mostafaKamal.moviesexplorer.views.fragments.movietrend.latestmovies


import com.mostafaKamal.moviesexplorer.datasource.data.Data
import com.mostafaKamal.moviesexplorer.datasource.repository.MovieRepository
import com.mostafaKamal.moviesexplorer.model.MovieModel
import com.mostafaKamal.moviesexplorer.model.Query
import com.mostafaKamal.moviesexplorer.views.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestMoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
) : BaseViewModel() {
    private var latestMovies: Data<MovieModel>? = null


    fun fetchLatestMovies(connectivityAvailable: Boolean): Data<MovieModel>? {
        if (latestMovies == null) {
            latestMovies =
                repository.observePagedMovieList(connectivityAvailable, this, Query.LATEST_MOVIES)
        }
        return latestMovies
    }

    fun refresh() {
        launch {
            repository.refreshMovieList(Query.LATEST_MOVIES)
        }
    }

}