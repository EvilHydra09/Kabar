package com.example.kabarubuntu.domain.usecase.appentry

import com.example.kabarubuntu.domain.manger.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }

}