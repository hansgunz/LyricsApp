package com.example.lyricsapp.presentation.viewmodel.states

import com.example.lyricsapp.presentation.viewmodel.utils.UiText

data class LyricsScreenState(
    val lyrics: String = "",
    val isLoading: Boolean = false,
    val error: UiText = UiText.DynamicString("")
)
