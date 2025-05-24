package com.np.fff.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.np.fff.data.model.RecipeDetail
import com.np.fff.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class RecipeViewModel : ViewModel() {

    private val _selectedRecipe = mutableStateOf<RecipeDetail?>(null)
    val selectedRecipe: State<RecipeDetail?> = _selectedRecipe

    fun loadRecipeDetail(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRecipeDetails(id)
                _selectedRecipe.value = response
            } catch (e: Exception) {
                // Error handling (npr. println(e.message))
            }
        }
    }
}
