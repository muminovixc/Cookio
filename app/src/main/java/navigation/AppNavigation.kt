package navigation

import androidx.compose.runtime.LaunchedEffect
import AnimatedSplashScreen
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.np.fff.presentation.RecipeViewModel
import ui.screen.home.DetailScreen
import ui.screen.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel=RecipeViewModel()

    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            AnimatedSplashScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("detail/{recipeId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("recipeId")?.toInt() ?: return@composable
            LaunchedEffect(id) {
                viewModel.loadRecipeDetail(id)
            }

            val recipe = viewModel.selectedRecipe.value
            if (recipe != null) {
                DetailScreen(navController,recipe = recipe)
            } else {
                CircularProgressIndicator()
            }
        }
    }
    }

