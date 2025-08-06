package com.example.lyricsapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.presentation.viewmodel.FavoritesViewModel
import com.example.lyricsapp.presentation.viewmodel.LyricsScreenViewModel
import com.example.lyricsapp.presentation.viewmodel.utils.UiText

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
    onItemClick: (LyricsData) -> Unit
){
    val state by viewModel.favoritesState.collectAsState()
     LaunchedEffect(key1 = true){
         viewModel.fetchLyricsFromLocal()
     }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ){
        when{
            state.lyricsList.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ){
                    items(state.lyricsList){ data ->
                        FavoriteItem(
                            modifier = Modifier,
                            lyricsData = data
                        ){
                            onItemClick(it)
                        }
                        HorizontalDivider()
                    }
                }
            }
            state.error != UiText.DynamicString("") -> {
                TODO()
            }
            state.isLoading -> {
                CircularProgressIndicator()
            }
        }
    }
}