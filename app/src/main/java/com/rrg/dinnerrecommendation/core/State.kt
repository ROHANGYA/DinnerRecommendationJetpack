package com.rrg.dinnerrecommendation.core

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Loaded<T>(val data: T) : State<T>()
    data class LoadingFailed(val error: AppError?) : State<Nothing>()
}
