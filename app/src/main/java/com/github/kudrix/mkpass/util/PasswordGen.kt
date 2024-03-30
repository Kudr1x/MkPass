package com.github.kudrix.mkpass.util

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.util.settings.DataStoreManager
import java.security.SecureRandom
import kotlin.random.Random
import kotlin.streams.toList

class PasswordGen(var password: Password, var context: Context) {
    var hashState: String by mutableStateOf("")
        private set

    var lenState: Int by mutableStateOf(0)
        private set

    fun updateHashState(hash: String){
        hashState = hash
    }

    fun updateLenState(len: Int){
        lenState = len
    }

    suspend fun getData(){
        DataStoreManager(context).getSettings().collect{
            updateHashState(it.masterPasswordHash)
            updateLenState(it.passwordLen)
        }
    }

    private val seed: String = hashState + password.service + password.login + password.version

    private val allCharacters = ('a'..'z') + ('A'..'Z') + (0..9) + ("!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~").toList()

    private val random = Random(seed.hashCode())

    suspend fun generatePassword(): String {
        getData()
        val list: List<Any> = List(lenState) { allCharacters[(random.nextInt(0, allCharacters.size))] }
        var password: String = ""
        for(i in list) password += i
        return password;
    }
}