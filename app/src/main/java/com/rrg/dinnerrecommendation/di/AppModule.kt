package com.rrg.dinnerrecommendation.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rrg.dinnerrecommendation.BuildConfig.BASE_URL_COCKTAIL
import com.rrg.dinnerrecommendation.BuildConfig.BASE_URL_MEAL
import com.rrg.dinnerrecommendation.BuildConfig.DEBUG
import com.rrg.dinnerrecommendation.api.CocktailApi
import com.rrg.dinnerrecommendation.api.MealApi
import com.rrg.dinnerrecommendation.core.NetworkRequestManager
import com.rrg.dinnerrecommendation.service.interceptor.CocktailSessionInterceptor
import com.rrg.dinnerrecommendation.service.interceptor.MealSessionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMealInterceptor(): MealSessionInterceptor {
        return MealSessionInterceptor()
    }

    @Provides
    @Singleton
    @MealInterceptor
    fun provideMealApiKeyInterceptor(
        mealSessionInterceptor: MealSessionInterceptor?
    ): OkHttpClient {
        val connectionTimeout: Long = 60
        val readTimeout: Long = 60

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)

        mealSessionInterceptor?.let {
            okHttpClientBuilder.addInterceptor(mealSessionInterceptor)
        }

        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideCocktailInterceptor(): CocktailSessionInterceptor {
        return CocktailSessionInterceptor()
    }

    @Provides
    @Singleton
    @CocktailInterceptor
    fun provideCocktailApiKeyInterceptor(
        cocktailSessionInterceptor: CocktailSessionInterceptor?
    ): OkHttpClient {
        val connectionTimeout: Long = 60
        val readTimeout: Long = 60

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)

        cocktailSessionInterceptor?.let {
            okHttpClientBuilder.addInterceptor(cocktailSessionInterceptor)
        }

        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    @MealRetrofit
    fun provideMealRetrofit(
        @MealInterceptor httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL_MEAL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    @CocktailRetrofit
    fun provideCocktailRetrofit(
        @CocktailInterceptor httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL_COCKTAIL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMealApi(@MealRetrofit retrofit: Retrofit): MealApi {
        return retrofit.create(MealApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCocktailApi(@CocktailRetrofit retrofit: Retrofit): CocktailApi {
        return retrofit.create(CocktailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkManager(): NetworkRequestManager{
        return NetworkRequestManager()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MealInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CocktailInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MealRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CocktailRetrofit
