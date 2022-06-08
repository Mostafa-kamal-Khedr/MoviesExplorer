package com.mostafaKamal.moviesexplorer.datasource.network.api

import com.bumptech.glide.load.engine.Resource


sealed class UseCaseResult<out T> {
    data class Success<out T>(val data : T) : UseCaseResult<T>()
    data class Error<out T>(val message: String? = "Unknown error", val data: T? = null): UseCaseResult<T>()
    class Loading<T>(data: T? = null) : UseCaseResult<T>()

}
