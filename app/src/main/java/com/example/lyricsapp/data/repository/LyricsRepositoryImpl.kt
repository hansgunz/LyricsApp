package com.example.lyricsapp.data.repository

import com.example.lyricsapp.data.local.LyricsDatabase
import com.example.lyricsapp.data.local.dao.LyricsDao
import com.example.lyricsapp.data.local.entity.LyricsEntity
import com.example.lyricsapp.data.remote.api.LyricApi
import com.example.lyricsapp.data.remote.model.DataDTO
import com.example.lyricsapp.domain.repository.LyricsRepository
import javax.inject.Inject

class LyricsRepositoryImpl @Inject constructor(
    private val lyricsApi: LyricApi,
    private val lyricsDao: LyricsDao
): LyricsRepository {

    override suspend fun getLyrics(artist: String, title: String): DataDTO {
        return lyricsApi.getLyrics(artist, title)
    }

    override suspend fun insertLyrics(lyricsEntity: LyricsEntity) {
        lyricsDao.insertLyrics(lyricsEntity)
    }

    override suspend fun fetchLyricsFromLocal(): List<LyricsEntity> {
        return lyricsDao.getAllLyrics()
    }
}