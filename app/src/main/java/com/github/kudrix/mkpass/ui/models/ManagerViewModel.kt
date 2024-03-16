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
    var fabState: Boolean by mutableStateOf(true)
        private set

    var iconExpandedFabState: Boolean by mutableStateOf(true)
        private set

    @Composable
    fun getPasswords(mainDb: MainDb): State<List<Password>> {
        return mainDb.dao.getAllPasswords().collectAsState(initial = emptyList())
    }

    fun updateFabState(){
        fabState = !fabState
    }

    fun updateIconExpandedFabState(){
        iconExpandedFabState = !iconExpandedFabState
    }

    fun addPassword(mainDb: MainDb) {
        viewModelScope.launch {
            mainDb.dao.insertPassword(
                Password(
                    null,
                    1,
                    "test",
                    "test.com",
                    ""
                ),
                )
        }
    }
}