package com.mostafaKamal.moviesexplorer.datasource.data.di


import com.mostafaKamal.moviesexplorer.datasource.data.MovieRemoteDataSource
import com.mostafaKamal.moviesexplorer.datasource.data.MovieRemoteDataSourceImpl
import com.mostafaKamal.moviesexplorer.datasource.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(apiService: ApiService):
            MovieRemoteDataSource = MovieRemoteDataSourceImpl(apiService)


}