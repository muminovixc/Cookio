package ui.screen.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.np.fff.data.model.RecipeDetail

fun extractListItemsFromHtml(html: String): List<String> {
    return Regex("<li>(.*?)</li>")
        .findAll(html)
        .map { it.groupValues[1].trim() }
        .toList()
}

@Composable
fun DetailScreen(navController: NavController, recipe: RecipeDetail) {
    val context = LocalContext.current
    val steps = extractListItemsFromHtml(recipe.instructions ?: "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                // Naslov
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Slika recepta sa klikom na Google
                AsyncImage(
                    model = recipe.image,
                    contentDescription = recipe.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            val encodedTitle = Uri.encode(recipe.title)
                            val searchUrl = "https://www.google.com/search?q=$encodedTitle"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl))
                            context.startActivity(intent)
                        }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sastojci
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    recipe.extendedIngredients.forEach {
                        Text(
                            text = "• ${it.original}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Upute
                Text(
                    text = "instructions",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (steps.isNotEmpty()) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        steps.forEachIndexed { index, step ->
                            Text(
                                text = "${index + 1}. $step",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                } else {
                    Text("no available instructions.", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Dugme za pretragu
                Button(
                    onClick = {
                        val encodedTitle = Uri.encode(recipe.title)
                        val url = "https://www.google.com/search?q=$encodedTitle"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Look for it on google")
                }
            }
        }
    }
}
