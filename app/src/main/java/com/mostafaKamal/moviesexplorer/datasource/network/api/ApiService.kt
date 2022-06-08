package com.mostafaKamal.moviesexplorer.datasource.network.api

import com.mostafaKamal.moviesexplorer.model.ResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/now_playing")
    fun getLatestMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Deferred<ResponseModel>

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Deferred<ResponseModel>

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Deferred<ResponseModel>


    @GET("3/genre/movie/list")
    fun getCategories(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Deferred<ResponseModel>

    @GET("3/discover/movie")
    fun getCategoryDetail(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("with_genres") id: Int
    ): Deferred<ResponseModel>


    @GET("3/search/movie")
    fun getSearch(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("query") query: String
    ): Deferred<ResponseModel>

}