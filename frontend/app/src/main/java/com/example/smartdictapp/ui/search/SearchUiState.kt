package com.example.smartdictapp.ui.search

data class SearchUiState(
    val outputLanguage: String = "English",
    val lookupMode: Boolean = false,
    val inputText: String = ""
)
