package com.github.kudrix.mkpass.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
<<<<<<< Updated upstream
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
=======
import androidx.compose.runtime.collectAsState
>>>>>>> Stashed changes
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kudrix.mkpass.R
<<<<<<< Updated upstream
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.ui.items.PasswordItem
import com.github.kudrix.mkpass.ui.models.ManagerViewModel
=======
import com.github.kudrix.mkpass.ui.items.PasswordItem
import com.github.kudrix.mkpass.ui.models.ManagerViewModel
import com.github.kudrix.mkpass.ui.models.PasswordListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
>>>>>>> Stashed changes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerScreen(passwordListViewModel: PasswordListViewModel) {
    val managerViewModel = viewModel<ManagerViewModel>()
    val passwords = passwordListViewModel.passwords.collectAsState().value

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
                        managerViewModel.updateAddPasswordDialogState()
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
                        imageVector =
                        if (managerViewModel.iconExpandedFabState)
                            Icons.Default.Menu
                        else
                            Icons.Default.Close,
                        contentDescription = "Expand or collapse menu",
                    )
                }
            }
        },

        content = {
            LazyColumn() {
<<<<<<< Updated upstream
                items(passwordStateList.value) {
                    PasswordItem(it, mainDb)
                }
            }


            //todo add in viewmodel
            if(managerViewModel.dialogState){
                var loginText by remember { mutableStateOf("") }
                var serviceText by remember { mutableStateOf("") }

=======
                itemsIndexed(passwords) { index, it ->
                    PasswordItem(
                        it,
                        passwordListViewModel,
                        showDialog = {managerViewModel.setPasswordForDelete(it, index)}
                    )
                }
            }

            if(managerViewModel.addPasswordDialogState){
>>>>>>> Stashed changes
                AlertDialog(
                    onDismissRequest = {
                        managerViewModel.updateAddPasswordDialogState()
                    },
                    title = {
                        Text(text = "Add password")
                    },
                    text = {
                        Column {
                            OutlinedTextField(
<<<<<<< Updated upstream
                                value = loginText,
                                onValueChange = { loginText = it },
=======
                                value = managerViewModel.loginText,
                                onValueChange = {
                                    managerViewModel.updateLoginText(it)
                                },
>>>>>>> Stashed changes
                                label = {
                                    Text(
                                        "Your login"
                                    )
                                }
                            )
                            OutlinedTextField(
<<<<<<< Updated upstream
                                value = serviceText,
                                onValueChange = { serviceText = it },
=======
                                value = managerViewModel.serviceText,
                                onValueChange = {
                                    managerViewModel.updateServiceText(it)
                                },
>>>>>>> Stashed changes
                                label = {
                                    Text(
                                        "Service name"
                                    )
                                }
                            )
                        }
                    },

                    confirmButton = {
                        Button(onClick = {
<<<<<<< Updated upstream
                            managerViewModel.addPassword(mainDb, loginText, serviceText)
                            managerViewModel.updateDialogState()
=======
//                            managerViewModel.addPassword(
//                                mainDb,
//                                managerViewModel.loginText,
//                                managerViewModel.serviceText
//                            )
                            passwordListViewModel.

                            managerViewModel.updateAddPasswordDialogState()
>>>>>>> Stashed changes
                        }) {
                            Text(
                                "Add"
                            )
                        }
                    },

                    dismissButton = {
                        Button(onClick = {
                            managerViewModel.updateAddPasswordDialogState()
                        }) {
                            Text(
                                "Cansel"
                            )
<<<<<<< Updated upstream
=======
                        }
                    }
                )
            }

            if(managerViewModel.deleteDialogState){
                AlertDialog(
                    onDismissRequest = {
                        managerViewModel.updateDeleteDialogState()
                    },
                    title = {
                        Text(text = "Delete password?")
                    },

                    confirmButton = {
                        Button(onClick = {
                            passwordListViewModel.delete(pas)

                            managerViewModel.updateDeleteDialogState()
                        }) {
                            Text(
                                "Yes"
                            )
                        }
                    },

                    dismissButton = {
                        Button(onClick = {
                            managerViewModel.updateDeleteDialogState()
                        }) {
                            Text(
                                "No"
                            )
>>>>>>> Stashed changes
                        }
                    }
                )
            }
        },
    )
}
