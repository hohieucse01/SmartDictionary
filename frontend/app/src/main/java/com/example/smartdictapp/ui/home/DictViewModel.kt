package com.example.smartdictapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DictViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(DictUiState())
    val uiState: StateFlow<DictUiState> = _uiState.asStateFlow()

    fun onEvent(dictEvent: DictEvent) {
        when (dictEvent) {
            is DictEvent.SetInputText -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        inputText = dictEvent.text
                    )
                }
            }

            is DictEvent.SetOutputLanguage -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        outputLanguage = dictEvent.language
                    )
                }
            }

            is DictEvent.ReturnSearch -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        inputText = ""
                    )
                }
            }
        }
    }
}