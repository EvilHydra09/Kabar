package com.example.kabarubuntu.di

import com.example.kabarubuntu.data.manager.LocalUserManagerImpl
import com.example.kabarubuntu.domain.manger.LocalUserManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

abstract class ManagerModule {


    @Binds
    @Singleton
    abstract fun bindLocalManager(localManager: LocalUserManagerImpl): LocalUserManager

}