package com.example.kabarubuntu.domain.usecase

import com.example.kabarubuntu.domain.manger.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }

}