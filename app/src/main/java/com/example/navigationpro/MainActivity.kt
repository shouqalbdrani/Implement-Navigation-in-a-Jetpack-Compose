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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

import com.example.navigationpro.ui.theme.NavigationProTheme

data class BottomNavigationItem( // add property of item in navigation bar title of icon , selected or not and the route if the user Select
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

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
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
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

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationItem("Home",
            Icons.Filled.Home,
            Icons.Outlined.Home,
            "FirstScreen"),
        BottomNavigationItem("Profile",
            Icons.Filled.Person,
            Icons.Outlined.Person,
            "SecondScreen"),
        BottomNavigationItem("Settings",
            Icons.Filled.Settings,
            Icons.Outlined.Settings,
            "ThirdScreen")
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route // take the current route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon, // change the state if the icon selected
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) }, // the title of icon
                selected = currentRoute == item.route, // check if the icon selected
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("FirstScreen") { inclusive = false } // does happen any replicate and for Ensure the first screen in the stack
                    }
                }
            )
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
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
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
            setTitle("Second Screen", true)
            SecondScreen(navController)
        }
        composable("ThirdScreen") {
            setTitle("Third Screen", true)
            ThirdScreen(navController)
        }
    }
}
