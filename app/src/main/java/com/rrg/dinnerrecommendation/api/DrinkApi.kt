package com.rrg.dinnerrecommendation.api

import com.rrg.dinnerrecommendation.models.primary.DrinkCategoryResponse
import com.rrg.dinnerrecommendation.models.primary.DrinkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {

    @GET("list.php?c=list")
    suspend fun getDrinkCategories(): Response<DrinkCategoryResponse>

    @GET("filter.php")
    suspend fun getListOfDrinksByCategory(
        @Query("c") category: String
    ): Response<DrinkResponse>

    @GET("lookup.php")
    suspend fun getDrinkById(
        @Query("i") id: String
    ): Response<DrinkResponse>

    @GET("search.php")
    suspend fun searchDrinks(
        @Query("s") searchQuery: String
    ): Response<DrinkResponse>
}
