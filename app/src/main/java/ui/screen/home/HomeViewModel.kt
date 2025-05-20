package ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.Recipe
import data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = RecipeRepository()

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchRecipes(query)
                _recipes.value = response.results
            } catch (e: Exception) {
                // handle error
                _recipes.value = emptyList()
            }
        }
    }
}
