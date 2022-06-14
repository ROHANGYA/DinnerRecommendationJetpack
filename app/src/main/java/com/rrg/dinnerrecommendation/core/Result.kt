package com.rrg.dinnerrecommendation.core

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val error: AppError) : Result<Nothing>()
}
