package com.rrg.dinnerrecommendation.api

import com.rrg.dinnerrecommendation.models.primary.MealCategoriesResponse
import com.rrg.dinnerrecommendation.models.primary.MealListByCategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MealApi {

    @GET("categories.php")
    suspend fun getMealCategories(): Response<MealCategoriesResponse>

    @GET("filter.php?c={category}")
    suspend fun getListOfMealsByCategory(
        @Path("category") category: String
    ): Response<MealListByCategoryResponse>
}
