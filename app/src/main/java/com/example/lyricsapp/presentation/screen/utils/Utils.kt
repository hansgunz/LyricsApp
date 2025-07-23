package com.example.lyricsapp.presentation.screen.utils

import java.util.Locale

fun String.artistAndSongFormatter(): String =
    split(" ").map {
            it.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        }.joinToString(" ")