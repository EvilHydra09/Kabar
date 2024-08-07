package com.example.kabarubuntu.domain.usecase.appentry

import com.example.kabarubuntu.domain.manger.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}