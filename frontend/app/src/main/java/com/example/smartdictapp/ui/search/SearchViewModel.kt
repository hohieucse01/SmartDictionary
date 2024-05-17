package com.example.smartdictapp.ui.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState : StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onEvent(searchEvent: SearchEvent) {
        when(searchEvent) {
            is SearchEvent.SetInputText -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        inputText = searchEvent.text
                    )}
            }
            is SearchEvent.SetOutputLanguage -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        outputLanguage = searchEvent.language
                    )}
            }
            is SearchEvent.Lookup -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        lookupMode = true
                    )}
            }
        }
    }
}