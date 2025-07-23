package com.example.lyricsapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lyricsapp.R
import com.example.lyricsapp.presentation.navigation.ScreenNavigationItem

@Composable
fun BottomBar(
    bottomItems: List<ScreenNavigationItem>,
    onItemClick: (ScreenNavigationItem) -> Unit
){
    BottomAppBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        tonalElevation = 8.dp,
        actions = {
            bottomItems.forEach {
                Column(
                    modifier = Modifier.weight(1F),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            onItemClick(it)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = it.title
                    )
                }
            }
        }
    )
}