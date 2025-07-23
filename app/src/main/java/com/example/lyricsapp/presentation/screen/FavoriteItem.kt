package com.example.lyricsapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.presentation.navigation.ScreenNavigationItem
import com.example.lyricsapp.presentation.screen.utils.artistAndSongFormatter

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    lyricsData: LyricsData,
    onItemClick: (LyricsData) -> Unit
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(lyricsData)
                }
        ) {
            Text(
                text = lyricsData.id.toString(),
                modifier = Modifier.weight(1F),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = lyricsData.artist.artistAndSongFormatter(),
                modifier = Modifier.weight(1F),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = lyricsData.song.artistAndSongFormatter(),
                modifier = Modifier.weight(1F),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}