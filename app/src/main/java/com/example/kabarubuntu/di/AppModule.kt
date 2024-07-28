package com.example.kabarubuntu.di

import android.app.Application
import com.example.kabarubuntu.data.manager.LocalUserManagerImpl
import com.example.kabarubuntu.domain.manger.LocalUserManager
import com.example.kabarubuntu.domain.usecase.AppEntryUseCase
import com.example.kabarubuntu.domain.usecase.ReadAppEntry
import com.example.kabarubuntu.domain.usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ):LocalUserManager{
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



}