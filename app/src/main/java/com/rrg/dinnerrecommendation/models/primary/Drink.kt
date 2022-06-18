package com.rrg.dinnerrecommendation.models.primary

data class Drink(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String,
    val strVideo: String? = null,
    val strInstructions: String? = null
)
