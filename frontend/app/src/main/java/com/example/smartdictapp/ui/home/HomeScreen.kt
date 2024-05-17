package com.example.smartdictapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("SmartDict") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }) {
        // Content of the screen
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding() + 16.dp,
                bottom = it.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {
            // Add your content here
            TextField(
                value = uiState.inputText,
                onValueChange = { },
                label = { Text("Enter a word") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true)
        }
    }
}