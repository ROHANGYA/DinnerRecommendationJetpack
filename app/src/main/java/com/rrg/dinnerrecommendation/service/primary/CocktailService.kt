package com.rrg.dinnerrecommendation.service.primary

import com.rrg.dinnerrecommendation.api.CocktailApi
import com.rrg.dinnerrecommendation.core.NetworkRequestManager
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.models.primary.CocktailCategoryResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailService @Inject constructor(
    private val networkRequestManager: NetworkRequestManager,
    private val cocktailApi: CocktailApi
) {
    suspend fun getCocktailCategories(): Result<CocktailCategoryResponse> {
        return networkRequestManager.apiRequest {
            cocktailApi.getCocktailCategories()
        }
    }
}
