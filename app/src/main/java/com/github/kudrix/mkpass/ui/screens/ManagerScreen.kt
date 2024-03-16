package com.github.kudrix.mkpass.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kudrix.mkpass.R
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.ui.items.PasswordItem
import com.github.kudrix.mkpass.ui.models.ManagerViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerScreen(mainDb: MainDb) {
    val managerViewModel = viewModel<ManagerViewModel>()
    val passwordStateList = managerViewModel.getPasswords(mainDb);
    val coroutinesScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            Column (
                horizontalAlignment = Alignment.End
            ){
                FloatingActionButton(
                    modifier = Modifier
                        .height(if (managerViewModel.fabState) 0.dp else 55.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search"
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                FloatingActionButton(
                    modifier = Modifier
                        .height(if (managerViewModel.fabState) 0.dp else 55.dp),
                    onClick = {
                        coroutinesScope.launch {
                            managerViewModel.addPassword(mainDb)
                        }
                    }){
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add"
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                ExtendedFloatingActionButton(
                    onClick = {
                        managerViewModel.updateIconExpandedFabState()
                        managerViewModel.updateFabState()
                    },
                ) {
                    Icon(
                        imageVector = if (managerViewModel.iconExpandedFabState) Icons.Default.Menu else Icons.Default.Close,
                        contentDescription = "Expand or collapse menu",
                    )
                }
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
