package com.example.navigationpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationpro.ui.theme.NavigationProTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationProTheme {
                val navController = rememberNavController()
                var title by remember { mutableStateOf("Home") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = { Text(title) })
                    }
                ) { innerPadding ->
                    NavigationHost(navController, innerPadding) { newTitle ->
                        title = newTitle
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    setTitle: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "FirstScreen",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("FirstScreen") {
            setTitle("First Screen")
            FirstScreen(navController)
        }
        composable("SecondScreen") {
            setTitle("Second Screen")
            SecondScreen(navController)
        }
    }
}