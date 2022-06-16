package com.rrg.dinnerrecommendation.service.primary

import com.rrg.dinnerrecommendation.api.MealApi
import com.rrg.dinnerrecommendation.core.NetworkRequestManager
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.models.primary.MealCategoriesResponse
import com.rrg.dinnerrecommendation.models.primary.MealListByCategoryResponse
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

    suspend fun getMealListByCategory(category: String): Result<MealListByCategoryResponse>{
        return networkRequestManager.apiRequest {
            mealApi.getListOfMealsByCategory(category)
        }
    }
}
