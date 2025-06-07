package ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.Recipe
import data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = RecipeRepository()

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        search(query)
    }

    private fun search(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchRecipes(query)
                _recipes.value = response.results
            } catch (e: Exception) {
                _recipes.value = emptyList()
            }
        }
    }
}
