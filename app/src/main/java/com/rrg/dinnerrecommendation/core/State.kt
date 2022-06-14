package com.rrg.dinnerrecommendation.core

sealed class State<out T> {
    object Loading : State<Nothing>()
    class Loaded<T>(val data: T) : State<T>()
    class LoadingFailed(val error: AppError?) : State<Nothing>()
}
