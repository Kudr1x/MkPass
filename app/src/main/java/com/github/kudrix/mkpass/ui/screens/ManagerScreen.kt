package com.github.kudrix.mkpass.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kudrix.mkpass.R
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.ui.items.PasswordItem
import com.github.kudrix.mkpass.ui.models.ManagerViewModel
import com.github.kudrix.mkpass.util.settings.DataStoreManager
import com.github.kudrix.mkpass.util.settings.SettingsData
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerScreen(mainDb: MainDb) {
    val managerViewModel = viewModel<ManagerViewModel>()
    val passwordStateList = managerViewModel.getPasswords(mainDb);

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
                        managerViewModel.updateDialogState()
                    }
                ){
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
                    PasswordItem(it)
                }
            }



            //todo add in viewmodel
            if(managerViewModel.dialogState){
                var text1 by remember { mutableStateOf("") }
                var text2 by remember { mutableStateOf("") }

                AlertDialog(
                    onDismissRequest = { },
                    title = {
                        Text(text = "My Dialog")
                    },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = text1,
                                onValueChange = { text1 = it },
                                label = { Text("Text 1") }
                            )
                            OutlinedTextField(
                                value = text2,
                                onValueChange = { text2 = it },
                                label = { Text("Text 2") }
                            )
                        }
                    },

                    confirmButton = {
                        Button(onClick = {
                            managerViewModel.addPassword(mainDb, text1, text2)
                            managerViewModel.updateDialogState()
                        }) {
                            Text("Добавить")
                        }
                    },

                    dismissButton = {
                        Button(onClick = {
                            managerViewModel.updateDialogState()
                        }) {
                            Text("Отмена")
                        }
                    }
                )
            }
        },
    )
}
