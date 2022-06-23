package com.rrg.dinnerrecommendation.service.primary

import com.rrg.dinnerrecommendation.api.DrinkApi
import com.rrg.dinnerrecommendation.core.NetworkRequestManager
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.models.primary.DrinkCategoryResponse
import com.rrg.dinnerrecommendation.models.primary.DrinkResponse
import com.rrg.dinnerrecommendation.models.primary.MealResponse
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

    suspend fun getDrinkListByCategory(category: String): Result<DrinkResponse> {
        return networkRequestManager.apiRequest {
            drinkApi.getListOfDrinksByCategory(category)
        }
    }

    suspend fun getDrinkById(id: String): Result<DrinkResponse> {
        return networkRequestManager.apiRequest {
            drinkApi.getDrinkById(id)
        }
    }

    suspend fun searchDrinks(searchQuery: String): Result<DrinkResponse> {
        return networkRequestManager.apiRequest {
            drinkApi.searchDrinks(searchQuery)
        }
    }
}
