package com.example.kabarubuntu.di

import android.app.Application
import androidx.room.Room
import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.data.local.NewsDatabase
import com.example.kabarubuntu.data.local.NewsTypeConvertor
import com.example.kabarubuntu.data.remote.NewsApi
import com.example.kabarubuntu.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // Add the OkHttpClient instance here
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ) : NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao

}