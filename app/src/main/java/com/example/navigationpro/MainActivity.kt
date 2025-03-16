package com.example.navigationpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

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
                var showBackButton by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CustomTopAppBar(
                            title = title,
                            showBackButton = showBackButton,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                ) { innerPadding ->
                    NavigationHost(navController, innerPadding) { newTitle, showBack ->
                        title = newTitle
                        showBackButton = showBack
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    showBackButton: Boolean,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back")
                }
            }
        }
    )
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    setTitle: (String, Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "FirstScreen",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("FirstScreen") {
            setTitle("First Screen", false)
            FirstScreen(navController)
        }
        composable("SecondScreen") {
            setTitle("Second Screen", true) // show the button o back if true
            SecondScreen(navController)
        }
        composable("ThirdScreen") {
            setTitle("Third Screen", true) // show the button o back if true
            ThirdScreen(navController)
        }
    }
}
