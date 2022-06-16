package com.rrg.dinnerrecommendation.api

import com.rrg.dinnerrecommendation.models.primary.MealCategoriesResponse
import com.rrg.dinnerrecommendation.models.primary.MealListByCategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("categories.php")
    suspend fun getMealCategories(): Response<MealCategoriesResponse>

    @GET("filter.php")
    suspend fun getListOfMealsByCategory(
        @Query("c") category: String
    ): Response<MealListByCategoryResponse>
}
