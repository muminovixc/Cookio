package com.np.fff.data.model

data class RecipeDetail(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String,
    val instructions: String?,
    val extendedIngredients: List<Ingredient>
)

data class Ingredient(
    val original: String
)
