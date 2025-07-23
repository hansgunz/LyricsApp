package com.example.lyricsapp.presentation.viewmodel.states

import com.example.lyricsapp.data.local.entity.LyricsEntity
import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.domain.usecase.result.DataError
import com.example.lyricsapp.presentation.viewmodel.utils.UiText

data class FavoritesScreenState(
    val lyricsList: List<LyricsData> = emptyList(),
    val error: UiText = UiText.DynamicString(""),
    val isLoading: Boolean = false
)
