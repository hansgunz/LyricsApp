package com.example.lyricsapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lyricsapp.data.local.LyricsDatabase
import com.example.lyricsapp.data.local.dao.LyricsDao
import com.example.lyricsapp.data.remote.api.LyricApi
import com.example.lyricsapp.data.repository.LyricsRepositoryImpl
import com.example.lyricsapp.domain.repository.LyricsRepository
import com.example.lyricsapp.domain.usecase.FetchLyricsFromLocalUseCase
import com.example.lyricsapp.domain.usecase.FetchLyricsFromLocalUseCaseImpl
import com.example.lyricsapp.domain.usecase.GetLyricsUseCase
import com.example.lyricsapp.domain.usecase.GetLyricsUseCaseImpl
import com.example.lyricsapp.domain.usecase.InsertLyricsUseCase
import com.example.lyricsapp.domain.usecase.InsertLyricsUseCaseImpl
import com.example.lyricsapp.domain.usecase.common.ErrorHandler
import com.example.lyricsapp.domain.usecase.common.IErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHTTPClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Const.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLyricsApi(retrofit: Retrofit): LyricApi{
        return retrofit.create(LyricApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLyricsDatabase(@ApplicationContext context: Context): LyricsDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = LyricsDatabase::class.java,
            name = "lyrics_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLyricsDao(lyricsDatabase: LyricsDatabase): LyricsDao{
        return lyricsDatabase.lyricsDao()
    }

    @Singleton
    @Provides
    fun provideLyricsRepository(lyricApi: LyricApi, lyricsDao: LyricsDao): LyricsRepository{
        return LyricsRepositoryImpl(
            lyricApi,
            lyricsDao
        )
    }

    @Singleton
    @Provides
    fun providesErrorHandler(): IErrorHandler {
        return ErrorHandler
    }

    @Singleton
    @Provides
    fun provideGetLyricsUseCase(
         lyricsRepository: LyricsRepository,
         errorHandler: IErrorHandler
    ): GetLyricsUseCase{
        return GetLyricsUseCaseImpl(
            lyricsRepository = lyricsRepository,
            errorHandler = errorHandler
        )
    }

    @Singleton
    @Provides
    fun provideInsertLyricsUseCase(repository: LyricsRepository): InsertLyricsUseCase {
        return InsertLyricsUseCaseImpl(
            repository
        )
    }

    @Singleton
    @Provides
    fun provideFetchLyricsFromLocalUseCase(repository: LyricsRepository, errorHandler: IErrorHandler)
    : FetchLyricsFromLocalUseCase{
        return FetchLyricsFromLocalUseCaseImpl(
            repository = repository,
            errorHandler = errorHandler
        )
    }
}