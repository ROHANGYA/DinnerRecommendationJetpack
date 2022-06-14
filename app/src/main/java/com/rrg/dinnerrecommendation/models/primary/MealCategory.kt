package com.rrg.dinnerrecommendation.models.primary

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealCategory(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    private val strCategoryDescription: String
) : Parcelable {

    fun getCleanCategoryDescription(): String {
        return strCategoryDescription.replace("[\\[1-9\\]]", "")
    }
}
