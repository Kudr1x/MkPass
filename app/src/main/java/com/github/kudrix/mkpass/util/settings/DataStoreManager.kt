package com.github.kudrix.mkpass.util.settings

import android.content.Context
import androidx.datastore.dataStore

private val Context.dataStore by dataStore("settings.json", SettingsSerializer)

class DataStoreManager(val context: Context) {

    suspend fun saveLen(len: Int){
        context.dataStore.updateData {data->
            data.copy(passwordLen = len)
        }
    }

    suspend fun saveMasterPasswordHash(hash: String){
        context.dataStore.updateData {data->
            data.copy(masterPasswordHash = hash)
        }
    }

    fun getSettings() = context.dataStore.data
}