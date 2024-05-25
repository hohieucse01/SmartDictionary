package com.example.smartdictapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smartdictapp.ui.home.DictEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    inputText: String,
    outputLanguage: String,
    onEvent: (DictEvent) -> Unit
) {
//    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("English", "Vietnamese")
    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "SmartDict",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(22.dp)
            )

            // Output text selection
            ExposedDropdownMenuBox(expanded = expanded,
                onExpandedChange = { expanded = !expanded }) {
                TextField(label = { Text("Output language") },
                    value = outputLanguage,
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
                            onEvent(DictEvent.SetOutputLanguage(text))
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

            // Input text field
            TextField(value = inputText,
                onValueChange = { onEvent(DictEvent.SetInputText(it)) },
                label = { Text("Enter a word") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    if (inputText.isNotEmpty()) {
                        // Hide keyboards
                        focusManager.clearFocus()
                        navController.navigate("result")
                    }
                })
            )
        }

    }
}
