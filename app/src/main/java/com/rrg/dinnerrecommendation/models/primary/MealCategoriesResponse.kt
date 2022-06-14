package com.rrg.dinnerrecommendation.models.primary

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealCategoriesResponse(
    val categories: List<MealCategories>
) : Parcelable {

    @Parcelize
    data class MealCategories(
        val idCategory: String,
        val strCategory: String,
        val strCategoryThumb: String,
        private val strCategoryDescription: String
    ) : Parcelable {

        fun getCleanCategoryDescription(): String {
            return strCategoryDescription.replace("[\\[1-9\\]]", "")
        }
    }
}
