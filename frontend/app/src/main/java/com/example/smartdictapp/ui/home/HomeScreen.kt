package com.example.smartdictapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartdictapp.ui.result.ResultScreen
import com.example.smartdictapp.ui.search.SearchScreen


@Composable
fun HomeScreen(viewModel: DictViewModel = DictViewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()


    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            SearchScreen(
                navController = navController,
                inputText = uiState.inputText,
                outputLanguage = uiState.outputLanguage,
                onEvent = viewModel::onEvent
            )
        }
        composable("result") {
            ResultScreen(inputText = uiState.inputText, navController = navController, onEvent = viewModel::onEvent)
        }
    }
}