package data.model

data class RecipeResponse(
    val results: List<Recipe>
)

data class Recipe(
    val id: Int,
    val title: String,
    val image: String
)
