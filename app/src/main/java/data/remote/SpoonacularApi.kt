package data.remote

import data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("number") number: Int = 108,
        @Query("apiKey") apiKey: String = "491f8ca4e2484e099dccb99a3b4cc8d4"
    ): RecipeResponse
}
