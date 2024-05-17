package com.example.smartdictapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState : StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(homeEvent: HomeEvent) {
        when(homeEvent) {
            is HomeEvent.SetInputText -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        inputText = homeEvent.text
                    )}
            }
            is HomeEvent.SetOutputLanguage -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        outputLanguage = homeEvent.language
                    )}
            }
            is HomeEvent.Lookup -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        lookupMode = true
                    )}
            }
        }
    }
}