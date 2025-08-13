package com.example.lyricsapp.data.mappers

import com.example.lyricsapp.data.local.entity.LyricsEntity
import com.example.lyricsapp.data.remote.model.DataDTO
import com.example.lyricsapp.domain.model.Data
import com.example.lyricsapp.domain.model.LyricsData

fun LyricsEntity.toLyricsData(): LyricsData{
    return LyricsData(
        id = id,
        artist = artist,
        song = song,
        lyrics = lyrics
    )
}

fun LyricsData.fromLyricsData(): LyricsEntity{
    return LyricsEntity(
        artist = artist,
        song = song,
        lyrics = lyrics
    )
}

fun DataDTO.toData(): Data{
    return Data(
        lyrics = lyrics
    )
}

