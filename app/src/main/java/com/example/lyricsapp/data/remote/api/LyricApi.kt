package com.example.lyricsapp.data.remote.api

import com.example.lyricsapp.data.remote.model.DataDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LyricApi {

    @GET("{artist}/{title}")
    suspend fun getLyrics(@Path("artist") artist: String, @Path("title") title: String): DataDTO

}