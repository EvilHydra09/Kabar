package com.example.kabarubuntu.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.kabarubuntu.domain.manger.LocalUserManager
import com.example.kabarubuntu.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserManagerImpl
   @Inject constructor (
    private val application: Application
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        application.dataStore.edit { setting ->
            setting[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map { preference ->
            preference[PreferencesKey.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constant.USER_SETTINGS)

object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(Constant.APP_ENTRY)

}