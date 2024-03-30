package com.github.kudrix.mkpass.ui.models

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.github.kudrix.mkpass.util.settings.DataStoreManager
import com.github.kudrix.mkpass.util.settings.SettingsData


const val MIN_PASSWORD_LENGTH: Int = 8;

class LoginViewModel : ViewModel() {
    var textField1: String by mutableStateOf("")
        private set

    var textField2: String by mutableStateOf("")
        private set

    var isErrorTextField1: Boolean by mutableStateOf(false)
        private set

    var isErrorTextField2: Boolean by mutableStateOf(false)
        private set

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

    fun updateErrorTextField1(flag: Boolean) {
        isErrorTextField1 = flag
    }

    fun updateErrorTextField2(flag: Boolean) {
        isErrorTextField2 = flag
    }

    fun updateTextField1(newText: String) {
        textField1 = newText
        updateErrorTextField1(false)
    }

    fun updateTextField2(newText: String) {
        textField2 = newText
        updateErrorTextField2(false)
    }

    var passwordVisibility1: Boolean by mutableStateOf(false)
        private set

    var passwordVisibility2: Boolean by mutableStateOf(false)
        private set

    fun updatePasswordVisibility1() {
        passwordVisibility1 = !passwordVisibility1
    }

    fun updatePasswordVisibility2() {
        passwordVisibility2 = !passwordVisibility2
    }

    suspend fun updateMasterPassword(masterPassword: String, context: Context){
        val dataStoreManager = DataStoreManager(context)
        dataStoreManager.saveMasterPasswordHash(masterPassword)
    }

    suspend fun checkPasswords(navController: NavController, context: Context){
        if(textField1 != textField2){
            updateErrorTextField2(true)
            return
        }

        if(textField1.length < MIN_PASSWORD_LENGTH){
            updateErrorTextField1(true)
            return
        }

        navController.navigate("ManagerScreen")

        updateMasterPassword(textField1, context)
    }

    fun startOnManger(navController: NavController){
        navController.navigate("ManagerScreen"){
            popUpTo(0)
        }
    }
}