package com.github.kudrix.mkpass.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.kudrix.mkpass.R
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.ui.items.PasswordItem
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerScreen(mainDb: MainDb, navController : NavController) {
    val passwordStateList = mainDb.dao.getAllPasswords().collectAsState(initial = emptyList())
    val coroutinesScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                coroutinesScope.launch {
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
            }){
                Icon(painter = painterResource(id = R.drawable.add), contentDescription = "Add")
            }
        },

        content = {
            LazyColumn() {
                items(passwordStateList.value) {
                    PasswordItem()
                }
            }
        }
    )
}