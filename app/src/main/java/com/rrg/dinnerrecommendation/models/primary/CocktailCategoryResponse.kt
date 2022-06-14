package com.rrg.dinnerrecommendation.models.primary

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CocktailCategoryResponse(
    val drinks: List<CocktailCategory>
) : Parcelable
