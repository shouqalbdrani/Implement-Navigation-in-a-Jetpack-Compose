package com.example.navigationpro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FirstScreen(navController: NavHostController,name: String,
                onNameChange: (String) -> Unit,
                age: String,
                onAgeChange: (String) -> Unit ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(text = " First Screen")

        TextField(
            modifier = Modifier.padding(20.dp),
            value = name,
            onValueChange =  onNameChange ,
            label = { Text("Name") }
        )

        TextField(
            modifier = Modifier.padding(10.dp),
            value = age,
            onValueChange = onAgeChange,
            label = { Text("Age") }
        )

        Button(onClick = {
            val info = "${name}-${age}"
            navController.navigate("SecondScreen/$info")
        }) {
            Text(text = "Go to the second screen")
        }
    }


}
