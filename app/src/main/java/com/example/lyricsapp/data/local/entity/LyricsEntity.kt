package com.example.lyricsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lyricsapp.domain.model.LyricsData

@Entity(tableName = "lyrics_entity")
data class LyricsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val artist: String,
    val song: String,
    val lyrics: String
){
    fun toLyrics(): LyricsData{
        return LyricsData(
            id = id,
            artist = artist,
            song = song,
            lyrics = lyrics
        )
    }
}
