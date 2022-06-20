package com.rrg.dinnerrecommendation.utils

class Constants {
    interface ScreenRoute {
        companion object {
            const val DINNER = "dinner"
            const val FOOD_BANK = "food_bank"
            const val SETTINGS = "settings"
            const val MEAL_CATEGORIES = "meal_categories"
            const val DRINK_CATEGORIES = "drink_categories"
            const val FINAL_RECOMMENDATION = "recommendation_page"
            const val RECIPE_DETAILS_FROM_RECOMMENDED = "Recipe_details_from_recommended"
            const val RECIPE_DETAILS_FROM_FOOD_BANK = "Recipe_details_from_food_bank"
        }
    }

    interface NavigationArguments {
        companion object {
            const val ID = "ID"
            const val TYPE = "TYPE"
        }
    }
}
