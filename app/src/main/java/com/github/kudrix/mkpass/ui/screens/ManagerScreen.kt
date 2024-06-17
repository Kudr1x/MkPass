package com.github.kudrix.mkpass.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kudrix.mkpass.R
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.ui.items.PasswordItem
import com.github.kudrix.mkpass.ui.models.ManagerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerScreen(mainDb: MainDb) {
    val managerViewModel = viewModel<ManagerViewModel>()
    val passwordStateList = managerViewModel.getPasswords(mainDb);

    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

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
                        imageVector = if (managerViewModel.iconExpandedFabState) Icons.Default.Menu else Icons.Default.Close,
                        contentDescription = "Expand or collapse menu",
                    )
                }
            }
        },

        content = {
            LazyColumn() {
                items(passwordStateList.value) {
                    PasswordItem(
                        it,
                        mainDb,
                        showBottomDialog = {
                            managerViewModel.setPasswordForDialog(it)
                            isBottomSheetVisible = true
                        }
                    )
                }
            }

            if (managerViewModel.addPasswordDialogState) {
                var loginText by remember { mutableStateOf("") }
                var serviceText by remember { mutableStateOf("") }

                AlertDialog(
                    onDismissRequest = { },
                    title = {
                        Text(text = "Add password")
                    },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = loginText,
                                onValueChange = { loginText = it },
                                label = {
                                    Text(
                                        "Your login"
                                    )
                                }
                            )
                            OutlinedTextField(
                                value = serviceText,
                                onValueChange = { serviceText = it },
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
                            managerViewModel.addPassword(mainDb, loginText, serviceText)
                            managerViewModel.updateAddPasswordDialogState()
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
                        }
                    }
                )
            }

            if (managerViewModel.deleteDialogState) {
                AlertDialog(
                    onDismissRequest = {
                        managerViewModel.updateDeleteDialogState()
                    },
                    title = {
                        Text(text = "Delete password?")
                    },

                    confirmButton = {
                        Button(onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                managerViewModel.deletePassword(mainDb)
                                managerViewModel.updateDeleteDialogState()
                            }
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
                        }
                    }
                )
            }

            BottomSheet(
                isBottomSheetVisible = isBottomSheetVisible,
                sheetState = sheetState,
                managerViewModel = managerViewModel,
                mainDb = mainDb,
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        isBottomSheetVisible = false
                    }
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    managerViewModel: ManagerViewModel,
    mainDb: MainDb
) {

    var slidersState by remember { mutableIntStateOf(0) }
    var isEdit by remember { mutableStateOf(false)}

    var newLogin by remember { mutableStateOf("") }
    var newLabel by remember { mutableStateOf("") }
    var newService by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope { Dispatchers.IO }

    if(managerViewModel.getPasswordForDialog() != null){
        newLogin = managerViewModel.getPasswordForDialog()?.login!!
        newLabel = managerViewModel.getPasswordForDialog()?.label!!
        newService = managerViewModel.getPasswordForDialog()?.service!!
    }

    if(managerViewModel.getPasswordForDialog() != null){
        slidersState = managerViewModel.getPasswordForDialog()!!.len!!
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
                isEdit = false
            },
            sheetState = sheetState,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp) // Outer padding
                    .clip(shape = RoundedCornerShape(24.dp))
                    .fillMaxWidth()
                    .padding(24.dp) // Inner padding
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newLogin,
                    onValueChange = {newLogin = it},
                    label = { Text(text = "Login") },
                    readOnly = !isEdit,
                    shape = RoundedCornerShape(12.dp),
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newService,
                    onValueChange = {newService = it},
                    label = { Text(text = "Service") },
                    readOnly = !isEdit,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = newLabel,
                    onValueChange = {newLabel = it},
                    label = { Text(text = "Label") },
                    readOnly = !isEdit,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        value = managerViewModel.getPasswordForDialog()!!.version.toString(),
                        onValueChange = {},
                        label = { Text(text = "Version") },
                        readOnly = true,
                        shape = RoundedCornerShape(12.dp)
                    )

                    FilledIconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.remove),
                            contentDescription = "Decrement"
                        )
                    }

                    FilledIconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Increment"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Slider(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth(0.85f),
                        value = slidersState.toFloat(),
                        enabled = isEdit,
                        onValueChange = {
                            CoroutineScope(Dispatchers.IO).launch {
                                slidersState = it.toInt()
                                managerViewModel.getPasswordForDialog()!!.len = it.toInt()
                            }
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.secondary,
                            activeTrackColor = MaterialTheme.colorScheme.secondary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        steps = 23,
                        valueRange = 16f..64f
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        text = slidersState.toString()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxWidth(0.4f)
                            .height(40.dp),
                        onClick = {},
                        content = { Text(text = "Delete") }
                    )

                    OutlinedButton(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxWidth(0.4f),
                        onClick = {
                            isEdit = !isEdit

                            if(!isEdit){
                                coroutineScope.launch {
                                    mainDb.dao.updatePassword(
                                        managerViewModel.getPasswordForDialog()!!.copy(
                                            login = newLogin,
                                            service = newService,
                                            label = newLabel
                                        )
                                    )
                                }

                                onDismiss()
                            }
                        },
                        content = {
                            if (!isEdit)
                                Text(text = "Edit")
                            else
                                Text(text = "Save")
                        }
                    )
                }
            }
        }
    }
}
