package com.example.lyricsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lyricsapp.data.local.dao.LyricsDao
import com.example.lyricsapp.data.local.entity.LyricsEntity

@Database(
    entities = [LyricsEntity::class],
    version = 1
)
abstract class LyricsDatabase: RoomDatabase() {
    abstract fun lyricsDao(): LyricsDao
}