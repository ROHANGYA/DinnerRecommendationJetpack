package com.rrg.dinnerrecommendation.api

import com.rrg.dinnerrecommendation.models.primary.MealCategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MealApi {

    @GET("categories.php")
    suspend fun getMealCategories(): Response<MealCategoriesResponse>
}
