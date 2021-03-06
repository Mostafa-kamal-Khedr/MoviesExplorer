package com.mostafaKamal.moviesexplorer.repository


import com.mostafaKamal.moviesexplorer.datasource.data.MovieRemoteDataSourceImpl
import com.mostafaKamal.moviesexplorer.datasource.local.dao.LatestMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.PopularMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.UpcomingMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.database.MovieAppDatabase
import com.mostafaKamal.moviesexplorer.datasource.network.api.ApiService
import com.mostafaKamal.moviesexplorer.datasource.repository.MovieRepository
import com.mostafaKamal.moviesexplorer.model.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MovieRepositoryTest {

    private lateinit var repository: MovieRepository
    private val latestMoviesDao = mock(LatestMoviesDao::class.java)
    private val popularMoviesDao = mock(PopularMoviesDao::class.java)
    private val upcomingMoviesDao = mock(UpcomingMoviesDao::class.java)

    private val service = mock(ApiService::class.java)
    private val remoteDataSource = MovieRemoteDataSourceImpl(service)


    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {
        val db = mock(MovieAppDatabase::class.java)
        `when`(db.latestMoviesDao()).thenReturn(latestMoviesDao)
        `when`(db.popularMoviesDao()).thenReturn(popularMoviesDao)
        `when`(db.upcomingMoviesDao()).thenReturn(upcomingMoviesDao)

        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository =
            MovieRepository(remoteDataSource, latestMoviesDao, upcomingMoviesDao, popularMoviesDao)
    }

    @Test
    fun loadLatestMoviesFromNetwork() {
        runBlocking {
            repository.observePagedMovieList(
                connectivityAvailable = true,
                coroutineScope = coroutineScope, query = Query.LATEST_MOVIES
            )

            verify(latestMoviesDao, never()).getLatestMovies()
            verifyNoInteractions(latestMoviesDao)
        }
    }

    @Test
    fun loadPopularMoviesFromNetwork() {
        runBlocking {
            repository.observePagedMovieList(
                connectivityAvailable = true,
                coroutineScope = coroutineScope, query = Query.POPULAR_MOVIES
            )

            verify(popularMoviesDao, never()).getPopularMovies()
            verifyNoInteractions(popularMoviesDao)
        }
    }

    @Test
    fun loadUpcomingMoviesFromNetwork() {
        runBlocking {
            repository.observePagedMovieList(
                connectivityAvailable = true,
                coroutineScope = coroutineScope, query = Query.UPCOMING_MOVIES
            )

            verify(upcomingMoviesDao, never()).getUpcomingMovies()
            verifyNoInteractions(upcomingMoviesDao)
        }
    }
}
