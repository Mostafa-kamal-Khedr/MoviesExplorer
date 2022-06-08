package com.mostafaKamal.moviesexplorer.datasource.repository.di


import com.mostafaKamal.moviesexplorer.datasource.data.MovieRemoteDataSource
import com.mostafaKamal.moviesexplorer.datasource.local.dao.LatestMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.PopularMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.UpcomingMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteDataSource: MovieRemoteDataSource,
        latestMoviesDao: LatestMoviesDao,
        upcomingMoviesDao: UpcomingMoviesDao,
        popularMoviesDao: PopularMoviesDao
    ): MovieRepository = MovieRepository(
        remoteDataSource = remoteDataSource,
        latestMoviesDao = latestMoviesDao,
        popularMoviesDao = popularMoviesDao,
        upcomingMoviesDao = upcomingMoviesDao,
    )

}