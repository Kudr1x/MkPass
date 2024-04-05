package com.github.kudrix.mkpass.ui.items

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.util.PasswordGen
import com.github.kudrix.mkpass.util.settings.SettingsData

class PasswordItemViewModel(private val mainDb: MainDb, private val password: Password) : ViewModel() {

    var version: String by mutableStateOf(password.version.toString())
        private set

    var isExpanded: Boolean by mutableStateOf(false)
        private set

    var isLabel: Boolean by mutableStateOf(false)
        private set

    fun updateIsExpanded(state: Boolean){
        isExpanded = state
    }

    fun updateIsLabel(state: Boolean){
        isLabel = state
    }

    fun changeVersion(newVersion: String) {
        try {
            mainDb.dao.updatePassword(password.copy(version = newVersion.toInt()))
        }catch (_: Exception){
            mainDb.dao.updatePassword(password.copy(version = 1))
        }
    }

    fun incrementVersion() {
        mainDb.dao.updatePassword(password.copy(version = version.toInt() + 1))
    }

    fun decrementVersion() {
        if(version.toInt() - 1 > 0){
            mainDb.dao.updatePassword(password.copy(version = version.toInt() - 1))
        }
    }

    fun deletePassword(password: Password) {
        //todo confirm dialog
    }

    fun copyPassword(
        context: Context,
        password: Password,
        clipboard: ClipboardManager,
        settingsState: State<SettingsData>
    ) {
        val passwordGen = PasswordGen(password, context, settingsState)
        clipboard.setText(AnnotatedString(passwordGen.generatePassword()))
    }
}