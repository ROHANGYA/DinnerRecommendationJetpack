package com.rrg.dinnerrecommendation.service.interceptor

import com.rrg.dinnerrecommendation.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class MealSessionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val urlWithApiKey = originalRequest.url.toString().replace("API_KEY",BuildConfig.API_KEY_MEAL)

        return BuildConfig.API_KEY_MEAL.let {
            val authenticatedRequest = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build()

            chain.proceed(authenticatedRequest)
        }
    }
}
