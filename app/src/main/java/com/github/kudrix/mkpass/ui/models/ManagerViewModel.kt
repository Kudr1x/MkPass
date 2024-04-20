package com.github.kudrix.mkpass.ui.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kudrix.mkpass.di.MainDb
import com.github.kudrix.mkpass.room.Password
import kotlinx.coroutines.launch

class ManagerViewModel() : ViewModel(){

    private var passwordForDelete: Password? = null

    fun setPasswordForDelete(password: Password, index: Int){
        passwordForDelete = password
    }

    fun deletePassword(mainDb: MainDb){
        if (passwordForDelete != null) {
            mainDb.passwordDao.deletePassword(passwordForDelete!!)
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
        return mainDb.passwordDao.getAllPasswords().collectAsState(initial = emptyList());
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
            mainDb.passwordDao.insertPassword(
                Password(
                    null,
                    1,
                    login,
                    service,
                    ""
                ),
            )
        }
    }
}