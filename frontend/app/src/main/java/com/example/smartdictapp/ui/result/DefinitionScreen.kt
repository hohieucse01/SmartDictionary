package com.example.smartdictapp.ui.result

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartdictapp.ui.home.DictEvent

@Composable
fun DefinitionScreen(
    inputText: String,
    onEvent: (DictEvent) -> Unit
) {
    var imageExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Word definition
                Text(
                    text = inputText, style = MaterialTheme.typography.headlineLarge
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

            // Synonyms
            WordCard(
                header = "Synonyms",
                wordList = listOf(
                    WordPair("word1", "meaning1"),
                    WordPair("word2", "meaning2"),
                    WordPair("word3", "meaning3"),
                    WordPair("word4", "meaning4"),
                    WordPair("word5", "meaning5"),
                    WordPair("word6", "meaning6"),
                    WordPair("word7", "meaning7"),
                    WordPair("word8", "meaning8"),
                    WordPair("word9", "meaning9"),
                    WordPair("word10", "meaning10"),
                )
            )

            // Antonyms
            WordCard(
                header = "Antonyms",
                wordList = listOf(
                    WordPair("word1", "meaning1"),
                    WordPair("word2", "meaning2"),
                    WordPair("word3", "meaning3"),
                    WordPair("word4", "meaning4"),
                    WordPair("word5", "meaning5"),
                    WordPair("word6", "meaning6"),
                    WordPair("word7", "meaning7"),
                    WordPair("word8", "meaning8"),
                    WordPair("word9", "meaning9"),
                    WordPair("word10", "meaning10"),
                )
            )

            // Photos
            PhotoCard()

        }
    }

}