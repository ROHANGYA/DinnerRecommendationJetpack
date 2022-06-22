package com.rrg.dinnerrecommendation.models.primary

data class Meal(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String,
    val strYoutube: String? = null,
    val strInstructions: String? = null,
    val strCategory: String? = null,
    val strArea: String? = null
)
