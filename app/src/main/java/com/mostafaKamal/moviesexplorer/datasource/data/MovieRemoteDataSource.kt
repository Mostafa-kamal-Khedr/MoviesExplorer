package com.mostafaKamal.moviesexplorer.datasource.data


import com.mostafaKamal.moviesexplorer.datasource.BaseDataSource
import com.mostafaKamal.moviesexplorer.datasource.network.api.ApiService
import com.mostafaKamal.moviesexplorer.datasource.network.api.UseCaseResult
import com.mostafaKamal.moviesexplorer.model.ResponseModel
import javax.inject.Inject

interface MovieRemoteDataSource {
    suspend fun getLatestMovies(page: Int): UseCaseResult<ResponseModel>
    suspend fun getPopularMovies(page: Int): UseCaseResult<ResponseModel>
    suspend fun getUpcomingMovies(page: Int): UseCaseResult<ResponseModel>
}

class MovieRemoteDataSourceImpl @Inject constructor(private val service: ApiService) :
    MovieRemoteDataSource, BaseDataSource() {

    private val apiKey = com.mostafaKamal.moviesexplorer.BuildConfig.API_KEY

    override suspend fun getLatestMovies(page: Int): UseCaseResult<ResponseModel> {
        return getResult { service.getLatestMovies(apiKey = apiKey, page = page) }
    }

    override suspend fun getPopularMovies(page: Int): UseCaseResult<ResponseModel> {
        return getResult { service.getPopularMovies(apiKey = apiKey, page = page) }
    }

    override suspend fun getUpcomingMovies(page: Int): UseCaseResult<ResponseModel> {
        return getResult { service.getUpcomingMovies(apiKey = apiKey, page = page) }
    }

}
