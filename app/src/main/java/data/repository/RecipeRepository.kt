package data.repository

import data.remote.ApiClient
import data.model.RecipeResponse

class RecipeRepository {
    suspend fun searchRecipes(query: String): RecipeResponse {
        return ApiClient.api.searchRecipes(query)
    }
}
