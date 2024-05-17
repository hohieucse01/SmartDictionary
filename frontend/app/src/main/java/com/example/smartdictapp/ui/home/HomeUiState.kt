package com.example.smartdictapp.ui.home

data class HomeUiState(
    val outputLanguage: String = "English",
    val lookupMode: Boolean = false,
    val inputText: String = ""
)
