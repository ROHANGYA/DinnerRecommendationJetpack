package com.rrg.dinnerrecommendation.core

import com.google.gson.JsonParseException
import dagger.Provides
import retrofit2.Response
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class NetworkRequestManager {
    suspend inline fun <reified T> apiRequest(crossinline apiCall: suspend () -> Response<T>): Result<T> {
        return try {
            val response = apiCall.invoke()
            return if (response.isSuccessful) {
                Timber.e(">> Successful api response >> ${response.body()}")
                val body = response.body()
                body?.let {
                    Result.Success(body)
                } ?: Result.Failure(AppError(AppError.Code.InvalidData))
            } else handleFailureInResponse(response)
        } catch (exception: Exception) {
            Timber.e(">>>error $exception ")
            handleFailureInRequest(exception)
        }
    }

    inline fun <reified T> handleFailureInRequest(throwable: Throwable): Result<T> {
        return if (throwable is JsonParseException) {
            Result.Failure(AppError(AppError.Code.DataSerialization, underlyingError = throwable))
        } else Result.Failure(AppError(AppError.Code.Network, underlyingError = throwable))
    }

    inline fun <reified T> handleFailureInResponse(response: Response<T>): Result<T> {
        return Result.Failure(getAppError(response))
    }

    fun <T> getAppError(response: Response<T>): AppError {

        val error = AppError(AppError.Code.BadRequest)
/*
        val responseError = Gson().fromJson(
            response.errorBody()?.charStream(),
            ServerError::class.java
        )*/

        return when (response.code()) {
            500 -> {
                AppError(
                    AppError.Code.ServerError
                )
            }
            else -> error
        }
    }
}
