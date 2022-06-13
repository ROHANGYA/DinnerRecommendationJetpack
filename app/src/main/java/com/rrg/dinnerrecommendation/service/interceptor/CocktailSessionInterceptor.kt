package com.rrg.dinnerrecommendation.service.interceptor

import com.rrg.dinnerrecommendation.BuildConfig
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class CocktailSessionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val urlWithApiKey = BuildConfig.BASE_URL_MEAL+ BuildConfig.API_KEY_MEAL+"/"

        return BuildConfig.API_KEY_MEAL.let {
            val authenticatedRequest = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build()

            chain.proceed(authenticatedRequest)
        }
    }
}