package com.example.smartdictapp.ui.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartdictapp.ui.home.DictEvent

@Composable
fun DefinitionScreen(
    inputText: String,
    onEvent: (DictEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Word definition
                Text(
                    text = inputText, style = MaterialTheme.typography.titleLarge
                )
                // Word pronunciation
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = "Pronounce")
                }
            }

            // Word definition
            Text(
                text = "Word definition goes here.", style = MaterialTheme.typography.bodyLarge
            )

            // Synonyms & antonyms

        }
    }

}