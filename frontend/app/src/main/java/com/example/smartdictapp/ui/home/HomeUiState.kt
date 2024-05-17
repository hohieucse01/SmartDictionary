package com.example.smartdictapp.ui.home

data class HomeUiState(
    val outputLanguage: String = "en",
    val lookupMode: Boolean = false,
    val inputText: String = ""
)
