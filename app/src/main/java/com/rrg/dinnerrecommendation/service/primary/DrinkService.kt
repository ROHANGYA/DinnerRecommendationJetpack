package com.rrg.dinnerrecommendation.service.primary

import com.rrg.dinnerrecommendation.api.DrinkApi
import com.rrg.dinnerrecommendation.core.NetworkRequestManager
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.models.primary.DrinkCategoryResponse
import com.rrg.dinnerrecommendation.models.primary.DrinkListByCategoryResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkService @Inject constructor(
    private val networkRequestManager: NetworkRequestManager,
    private val drinkApi: DrinkApi
) {
    suspend fun getDrinkCategories(): Result<DrinkCategoryResponse> {
        return networkRequestManager.apiRequest {
            drinkApi.getDrinkCategories()
        }
    }

    suspend fun getDrinkListByCategory(category: String): Result<DrinkListByCategoryResponse> {
        return networkRequestManager.apiRequest {
            drinkApi.getListOfDrinksByCategory(category)
        }
    }
}
