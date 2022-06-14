package com.rrg.dinnerrecommendation.api

import com.rrg.dinnerrecommendation.models.primary.CocktailCategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApi {

    @GET("list.php?c=list")
    suspend fun getCocktailCategories(): Response<CocktailCategoryResponse>
}
