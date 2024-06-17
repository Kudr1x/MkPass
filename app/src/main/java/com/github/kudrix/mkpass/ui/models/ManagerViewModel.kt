package com.github.kudrix.mkpass.ui.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
import kotlinx.coroutines.launch

class ManagerViewModel() : ViewModel(){

    private var passwordForDelete: Password? = null
    private var passwordForDialog: Password? = null

    fun setPasswordForDelete(password: Password){
        passwordForDelete = password
    }

    fun setPasswordForDialog(password: Password){
        passwordForDialog = password
    }

    fun getPasswordForDialog(): Password?{
        return passwordForDialog
    }

    fun deletePassword(mainDb: MainDb){
        if (passwordForDelete != null) {
            mainDb.dao.deletePassword(passwordForDelete!!)
        }

        passwordForDelete = null
    }

    var fabState: Boolean by mutableStateOf(true)
        private set

    var iconExpandedFabState: Boolean by mutableStateOf(true)
        private set

    var addPasswordDialogState: Boolean by mutableStateOf(false)
        private set

    var loginText: String by mutableStateOf("")
        private set

    var serviceText: String by mutableStateOf("")
        private set

    var deleteDialogState: Boolean by mutableStateOf(false)
        private set

    fun updateLoginText(text: String){
        loginText = text;
    }

    fun updateServiceText(text: String){
        serviceText = text;
    }

    fun updateDeleteDialogState(){
        deleteDialogState = !deleteDialogState
    }

    @Composable
    fun getPasswords(mainDb: MainDb): State<List<Password>> {
        val list =  mainDb.dao.getAllPasswords().collectAsState(initial = emptyList());

        return list;
    }

    fun updateAddPasswordDialogState(){
        addPasswordDialogState = !addPasswordDialogState
    }

    fun updateFabState(){
        fabState = !fabState
    }

    fun updateIconExpandedFabState(){
        iconExpandedFabState = !iconExpandedFabState
    }

    fun addPassword(mainDb: MainDb, login: String, service: String) {
        viewModelScope.launch {
            val currentPassword = Password(null, 1, login, service, "")
            mainDb.dao.insertPassword(currentPassword)
        }
    }

    fun updatePassword() {

    }
}