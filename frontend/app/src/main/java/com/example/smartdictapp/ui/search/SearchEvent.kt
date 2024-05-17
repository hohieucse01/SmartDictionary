package com.example.smartdictapp.ui.search

sealed interface SearchEvent {
    data class SetInputText(val text: String) : SearchEvent
    data class SetOutputLanguage(val language: String) : SearchEvent
    object Lookup : SearchEvent
}