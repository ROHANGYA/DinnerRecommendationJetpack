package com.rrg.dinnerrecommendation.utils

class Constants {
    interface ScreenRoute {
        companion object {
            const val RECOMMENDATION = "recommendation"
            const val FOOD_BANK = "food_bank"
            const val SETTINGS = "settings"
            const val MEAL_CATEGORIES = "meal_categories"
            const val DRINK_CATEGORIES = "drink_categories"
            const val FINAL_RECOMMENDATION = "recommendation_page"
            const val RECIPE_DETAILS = "Recipe_details"
        }
    }

    interface NavigationArguments {
        companion object {
            const val ID = "ID"
            const val TYPE = "TYPE"
        }
    }
}
