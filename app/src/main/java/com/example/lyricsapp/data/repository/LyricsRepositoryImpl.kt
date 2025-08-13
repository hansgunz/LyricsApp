package com.example.lyricsapp.data.repository

import com.example.lyricsapp.data.local.LyricsDatabase
import com.example.lyricsapp.data.local.dao.LyricsDao
import com.example.lyricsapp.data.local.entity.LyricsEntity
import com.example.lyricsapp.data.mappers.fromLyricsData
import com.example.lyricsapp.data.mappers.toData
import com.example.lyricsapp.data.mappers.toLyricsData
import com.example.lyricsapp.data.remote.api.LyricApi
import com.example.lyricsapp.data.remote.model.DataDTO
import com.example.lyricsapp.domain.model.Data
import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.domain.repository.LyricsRepository
import javax.inject.Inject

class LyricsRepositoryImpl @Inject constructor(
    private val lyricsApi: LyricApi,
    private val lyricsDao: LyricsDao
): LyricsRepository {

    override suspend fun getLyrics(artist: String, title: String): Data {
        return lyricsApi.getLyrics(artist, title).toData()
    }

    override suspend fun insertLyrics(lyricsData: LyricsData) {
        lyricsDao.insertLyrics(lyricsData.fromLyricsData())
    }

    override suspend fun fetchLyricsFromLocal(): List<LyricsData> {
        return lyricsDao.getAllLyrics().map { it.toLyricsData() }
    }
}