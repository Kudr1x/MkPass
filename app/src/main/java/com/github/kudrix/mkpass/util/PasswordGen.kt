package com.github.kudrix.mkpass.util

import android.content.Context
import androidx.compose.runtime.State
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.util.settings.SettingsData
import kotlin.random.Random

class PasswordGen(var password: Password, var context: Context, settingsState: State<SettingsData>) {
    private val settings: State<SettingsData> = settingsState

    private val seed: String = settings.value.masterPasswordHash + password.service + password.login + password.version

    private val allCharacters = ('a'..'z') + ('A'..'Z') + (0..9) + ("!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~").toList()

    private val random = Random(seed.hashCode())

    fun generatePassword(): String {
        val list: List<Any> = List(password.len!!) { allCharacters[(random.nextInt(0, allCharacters.size))] }
        var password = ""
        for(i in list) password += i

        return password;
    }
}