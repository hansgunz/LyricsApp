package com.example.lyricsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lyricsapp.data.local.entity.LyricsEntity

@Dao
interface LyricsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLyrics(lyricsEntity: LyricsEntity)

    @Query("Select * from lyrics_entity")
    suspend fun getAllLyrics(): List<LyricsEntity>

}