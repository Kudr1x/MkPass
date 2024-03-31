package com.github.kudrix.mkpass.ui.items

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.util.PasswordGen
import com.github.kudrix.mkpass.util.settings.SettingsData

class PasswordItemViewModel() : ViewModel() {

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