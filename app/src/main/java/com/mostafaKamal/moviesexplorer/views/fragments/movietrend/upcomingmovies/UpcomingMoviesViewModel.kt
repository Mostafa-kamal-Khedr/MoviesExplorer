package com.mostafaKamal.moviesexplorer.views.fragments.movietrend.upcomingmovies


import com.mostafaKamal.moviesexplorer.datasource.data.Data
import com.mostafaKamal.moviesexplorer.datasource.repository.MovieRepository
import com.mostafaKamal.moviesexplorer.model.MovieModel
import com.mostafaKamal.moviesexplorer.model.Query
import com.mostafaKamal.moviesexplorer.views.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
) : BaseViewModel() {
    private var upcomingMovies: Data<MovieModel>? = null


    fun fetchUpcomingMovies(connectivityAvailable: Boolean): Data<MovieModel>? {
        if (upcomingMovies == null) {
            upcomingMovies =
                repository.observePagedMovieList(connectivityAvailable, this, Query.UPCOMING_MOVIES)
        }
        return upcomingMovies
    }

    fun refresh() {
        launch {
            repository.refreshMovieList(Query.UPCOMING_MOVIES)
        }
    }
}