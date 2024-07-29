package com.example.kabarubuntu.di

import android.app.Application
import com.example.kabarubuntu.data.manager.LocalUserManagerImpl
import com.example.kabarubuntu.data.remote.NewsApi
import com.example.kabarubuntu.data.repository.NewsRepositoryImpl
import com.example.kabarubuntu.domain.manger.LocalUserManager
import com.example.kabarubuntu.domain.repository.NewsRepository
import com.example.kabarubuntu.domain.usecase.appentry.AppEntryUseCase
import com.example.kabarubuntu.domain.usecase.appentry.ReadAppEntry
import com.example.kabarubuntu.domain.usecase.appentry.SaveAppEntry
import com.example.kabarubuntu.domain.usecase.news.GetNews
import com.example.kabarubuntu.domain.usecase.news.NewsUseCases
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
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager {
        return LocalUserManagerImpl(application)
    }


    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManager: LocalUserManager
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

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
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi)
    }


    @Provides
    @Singleton
    fun provideNewsUseCase(
       newsRepository: NewsRepository
    ) = NewsUseCases(
        getNews = GetNews(newsRepository)
    )


}