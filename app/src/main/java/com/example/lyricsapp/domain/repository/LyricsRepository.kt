package com.example.lyricsapp.domain.repository

import com.example.lyricsapp.data.local.entity.LyricsEntity
import com.example.lyricsapp.data.remote.model.DataDTO

interface LyricsRepository {

    suspend fun getLyrics(artist: String, title: String): DataDTO

    suspend fun insertLyrics(lyricsEntity: LyricsEntity)

    suspend fun fetchLyricsFromLocal(): List<LyricsEntity>
}