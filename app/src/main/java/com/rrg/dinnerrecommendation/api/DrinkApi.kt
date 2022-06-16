package com.rrg.dinnerrecommendation.api

import com.rrg.dinnerrecommendation.models.primary.DrinkCategoryResponse
import com.rrg.dinnerrecommendation.models.primary.DrinkListByCategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DrinkApi {

    @GET("list.php?c=list")
    suspend fun getDrinkCategories(): Response<DrinkCategoryResponse>

    @GET("filter.php?c={category}")
    suspend fun getListOfDrinksByCategory(
        @Path("category") category: String
    ): Response<DrinkListByCategoryResponse>
}
