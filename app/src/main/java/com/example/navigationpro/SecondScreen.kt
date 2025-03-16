package com.example.navigationpro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SecondScreen(navController: NavHostController, info: String?) {
    val (name, age) = info?.split("-") ?: listOf("No Name", "No Age")
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Text(text = "Second Screen")
        Text(text = "Name: $name")
        Text(text = "Age: $age")
        Text(text = "The information is: ${info ?: "No Data"}")


        Button(onClick = { navController.navigate("ThirdScreen") }) {
            Text(text = "Go to the Third Screen")
        }

    }
}
