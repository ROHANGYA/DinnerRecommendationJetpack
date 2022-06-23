package com.rrg.dinnerrecommendation.models.primary

import com.rrg.dinnerrecommendation.models.keys.RecipeCategories

data class FoodBankItem(
    val name: String,
    val thumb: String,
    val id: String,
    val category: String? = null,
    val area: String? = null,
    val strAlcoholic: String? = null,
    val type: RecipeCategories
)
