package com.example.navigationpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationpro.ui.theme.NavigationProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationProTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                    NavigationHost(innerPadding)
                }
            }
        }
    }
}

@Composable
fun NavigationHost(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FirstScreen",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("FirstScreen") { FirstScreen(navController) }
        composable("SecondScreen") { SecondScreen(navController) }
    }
}
