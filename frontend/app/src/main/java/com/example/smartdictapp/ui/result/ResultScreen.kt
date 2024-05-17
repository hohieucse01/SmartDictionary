package com.example.smartdictapp.ui.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smartdictapp.ui.home.DictEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(inputText: String, onEvent: (DictEvent) -> Unit,  navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = inputText)
        }, navigationIcon = {
            IconButton(onClick = {
                onEvent(DictEvent.ReturnSearch)
                navController.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding() + 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
        {
            Text("Hello World!")
        }


    }
}