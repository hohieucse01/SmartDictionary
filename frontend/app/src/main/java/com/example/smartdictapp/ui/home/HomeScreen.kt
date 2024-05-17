package com.example.smartdictapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("English", "Vietnamese")

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            TopAppBar(
//                title = { Text("SmartDict") },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
//            )
            Text(
                "SmartDict",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(22.dp)
            )

            // Output text selection
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }) {
                TextField(
                    value = uiState.outputLanguage,
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = {
                        TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .padding(end = 16.dp, bottom = 8.dp, top = 8.dp)
                        .fillMaxWidth(0.7f)
                )

                // Dropdown menu
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    options.forEach { text ->
                        DropdownMenuItem(onClick = {
                            viewModel.onEvent(HomeEvent.SetOutputLanguage(text))
                            expanded = false
                        }, text = { Text(text) })
                    }
                }
            }
        }

    }) { paddingValues ->
        // Content of the screen
        Column(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding() + 16.dp,
                bottom = paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {
            if (!uiState.lookupMode) {
                // Input text field
                TextField(
                    value = uiState.inputText,
                    onValueChange = { viewModel.onEvent(HomeEvent.SetInputText(it)) },
                    label = { Text("Enter a word") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = { viewModel.onEvent(HomeEvent.Lookup) }
                    )
                )
            }
            else {

            }

        }
    }
}