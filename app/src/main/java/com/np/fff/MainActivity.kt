package com.np.fff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.np.fff.ui.theme.FffTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FffTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { Zlato(navController) }
        composable("details") { Srebro() }
    }
}

@Composable
fun Zlato(navController: NavController) {
    val viewModel: MyViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .padding(top = 50.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(40.dp))
            Text("Bodovi 1. igrača:", fontSize = 20.sp, color = Color.Green)
            Spacer(modifier = Modifier.width(50.dp))
            Text("Bodovi 2. igrača:", fontSize = 20.sp, color = Color.Yellow)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                "${viewModel.bodovi1}",
                color = Color.Yellow,
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(start = 90.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.Gray)
            )
            Text(
                "${viewModel.bodovi2}",
                color = Color.Yellow,
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(start = 160.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.Gray)
            )
        }
        Text(
            "Igrač Broj ${viewModel.Igraigrac} je dobio ${viewModel.iskopano}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(25.dp),
            fontSize = 30.sp,
            color = Color.Black
        )
        Row(modifier = Modifier.padding(top = 50.dp)) {
            Button(
                onClick = { navController.navigate("details") },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text("Kopaj-Igrač 1")
            }
            Button(
                onClick = { viewModel.potezIgrac2() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kopaj-igrač 2")
            }
        }
        Text(
            "Na redu je igrač broj ${viewModel.Igraigrac}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(25.dp),
            fontSize = 30.sp,
            color = Color.Black
        )
        Column {
            if (viewModel.bodovi1 > 9) {
                Text("Pobijedio je igrač broj 1")
                Button(
                    onClick = { viewModel.reset() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Restart")
                }
            }
            if (viewModel.bodovi2 > 9) {
                Text("Pobijedio je igrač broj 2")
                Button(
                    onClick = { viewModel.reset() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Restart")
                }
            }
        }
    }
}
