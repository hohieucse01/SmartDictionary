package com.example.smartdictapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.smartdictapp.ui.home.HomeScreen
import com.example.smartdictapp.ui.theme.SmartDictAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartDictAppTheme {
                // A surface container using the 'background' color from the theme
                HomeScreen()
            }
        }
    }
}