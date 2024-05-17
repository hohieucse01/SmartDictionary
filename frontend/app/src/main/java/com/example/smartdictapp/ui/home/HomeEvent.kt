package com.example.smartdictapp.ui.home

sealed interface HomeEvent {
    data class SetInputText(val text: String) : HomeEvent
    data class SetOutputLanguage(val language: String) : HomeEvent
    object Lookup : HomeEvent
}