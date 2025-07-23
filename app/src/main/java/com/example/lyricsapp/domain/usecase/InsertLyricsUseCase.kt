package com.example.lyricsapp.domain.usecase

interface InsertLyricsUseCase {

    suspend operator fun invoke(artist: String, song: String, lyrics: String)
}