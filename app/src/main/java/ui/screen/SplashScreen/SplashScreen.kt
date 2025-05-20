import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import java.time.format.TextStyle

@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )
    val scaleAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim.value)
                .scale(scaleAnim.value),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Receipts",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.3f),
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                )
            )
        }
    }
}
