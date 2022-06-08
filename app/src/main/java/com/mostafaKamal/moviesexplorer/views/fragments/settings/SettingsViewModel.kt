package com.mostafaKamal.moviesexplorer.views.fragments.settings

import androidx.lifecycle.viewModelScope

import com.mostafaKamal.moviesexplorer.datasource.repository.MovieRepository
import com.mostafaKamal.moviesexplorer.util.SingleLiveEvent
import com.mostafaKamal.moviesexplorer.views.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: MovieRepository,
) : BaseViewModel() {
    var showLoading = SingleLiveEvent<Boolean>()
    var clearAppListener = SingleLiveEvent<String>()

    fun clearAppData(){
        showLoading.value = true
        delayFor(3000) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.latestMoviesDao.deleteAllLatestMovies()
                repository.upcomingMoviesDao.deleteAllUpcomingMovies()
                repository.popularMoviesDao.deleteAllPopularMovies()
                showLoading.postValue(false)
                clearAppListener.postValue("")
            }
        }
    }
}