package com.mostafaKamal.moviesexplorer.datasource

import com.mostafaKamal.moviesexplorer.datasource.network.api.UseCaseResult
import kotlinx.coroutines.Deferred
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: () -> Deferred<T>): UseCaseResult<T> {

        return try {
            val response = call().await()
            UseCaseResult.Success(response)
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): UseCaseResult<T> {

        Timber.e(message)
        return UseCaseResult.Error("Network call has failed for a following reason: $message")
    }
}

