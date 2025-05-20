package navigation


import AnimatedSplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.screen.home.DetailScreen
import ui.screen.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            AnimatedSplashScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("details") {
            DetailScreen(navController)
        }
    }
}
