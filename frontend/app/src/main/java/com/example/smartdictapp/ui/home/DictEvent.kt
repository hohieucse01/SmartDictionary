package com.example.smartdictapp.ui.home

sealed interface DictEvent {
    data class SetInputText(val text: String) : DictEvent
    data class SetOutputLanguage(val language: String) : DictEvent
    object ReturnSearch : DictEvent
}