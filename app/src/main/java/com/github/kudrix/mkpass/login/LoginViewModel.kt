package com.github.kudrix.mkpass.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

const val MIN_PASSWORD_LENGTH: Int = 8;

class LoginViewModel : ViewModel() {
    var textField1: String by mutableStateOf("")
        private set

    var textField2: String by mutableStateOf("")
        private set

    fun updateTextField1(newText: String) {
        textField1 = newText
    }

    fun updateTextField2(newText: String) {
        textField2 = newText
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

    fun checkPasswords(navController: NavController){

        //todo check
        if(true){
            navController.navigate("ManagerScreen")
        }else{
            //todo
        }
    }
}