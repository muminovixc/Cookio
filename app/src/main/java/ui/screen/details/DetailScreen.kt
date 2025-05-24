package ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.np.fff.data.model.RecipeDetail
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

fun extractListItemsFromHtml(html: String): List<String> {
    return Regex("<li>(.*?)</li>")
        .findAll(html)
        .map { it.groupValues[1].trim() }
        .toList()
}


@Composable
fun DetailScreen(navController: NavController, recipe: RecipeDetail) {

    val steps = extractListItemsFromHtml(recipe.instructions ?: "")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(12.dp))

                AsyncImage(
                    model = recipe.image,
                    contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(16.dp))

                Text("Sastojci:", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                recipe.extendedIngredients.forEach {
                    Text("• ${it.original}", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(Modifier.height(16.dp))

                Text("Upute:", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Column {
                    if (steps.isNotEmpty()) {
                        steps.forEachIndexed { index, step ->
                            Text("${index + 1}. $step")
                            Spacer(Modifier.height(4.dp))
                        }
                    } else {
                        Text("Nema dostupnih uputa.")
                    }
                }
            }
        }
    }
}


