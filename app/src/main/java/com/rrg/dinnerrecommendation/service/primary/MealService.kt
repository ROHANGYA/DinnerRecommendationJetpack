package com.rrg.dinnerrecommendation.service.primary

import com.rrg.dinnerrecommendation.api.MealApi
import com.rrg.dinnerrecommendation.core.NetworkRequestManager
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.models.primary.MealCategoriesResponse
import com.rrg.dinnerrecommendation.models.primary.MealResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealService @Inject constructor(
    private val networkRequestManager: NetworkRequestManager,
    private val mealApi: MealApi
) {

    suspend fun getMealCategories(): Result<MealCategoriesResponse> {
        return networkRequestManager.apiRequest {
            mealApi.getMealCategories()
        }
    }

    suspend fun getMealListByCategory(category: String): Result<MealResponse> {
        return networkRequestManager.apiRequest {
            mealApi.getListOfMealsByCategory(category)
        }
    }

    suspend fun getMealById(id: String): Result<MealResponse> {
        return networkRequestManager.apiRequest {
            mealApi.getMealById(id)
        }
    }

    suspend fun searchMeals(searchQuery: String): Result<MealResponse> {
        return networkRequestManager.apiRequest {
            mealApi.searchMeals(searchQuery)
        }
    }
}
