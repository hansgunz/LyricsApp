package com.example.lyricsapp.domain.usecase

import com.example.lyricsapp.data.local.entity.LyricsEntity
import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.domain.repository.LyricsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertLyricsUseCaseImpl @Inject constructor(private val repository: LyricsRepository): InsertLyricsUseCase {

    override suspend fun invoke(artist: String, song: String, lyrics: String) {
        repository.insertLyrics(
            LyricsData(
                artist = artist,
                song = song,
                lyrics = lyrics
            )
        )
    }
}