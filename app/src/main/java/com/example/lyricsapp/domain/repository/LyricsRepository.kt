package com.example.lyricsapp.domain.repository

import com.example.lyricsapp.data.local.entity.LyricsEntity
import com.example.lyricsapp.data.remote.model.DataDTO
import com.example.lyricsapp.domain.model.Data
import com.example.lyricsapp.domain.model.LyricsData

interface LyricsRepository {

    suspend fun getLyrics(artist: String, title: String): Data

    suspend fun insertLyrics(lyricsData: LyricsData)

    suspend fun fetchLyricsFromLocal(): List<LyricsData>
}