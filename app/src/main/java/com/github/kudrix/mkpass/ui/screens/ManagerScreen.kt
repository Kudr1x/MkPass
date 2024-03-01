package com.github.kudrix.mkpass.ui.screens

import android.annotation.SuppressLint
import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kudrix.mkpass.custom.multiFloatingActionButton.FabButtonItem
import com.github.kudrix.mkpass.custom.multiFloatingActionButton.FabButtonMain
import com.github.kudrix.mkpass.custom.multiFloatingActionButton.FabButtonSub
import com.github.kudrix.mkpass.custom.multiFloatingActionButton.MultiFloatingActionButton
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
import com.github.kudrix.mkpass.ui.items.PasswordItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ManagerScreen(mainDb: MainDb) {
    var bottomAppBarHeight by remember { mutableStateOf(0.dp) }
    var isBottomAppBarRaised by remember { mutableStateOf(false) }
    val passwordStateList = mainDb.dao.getAllPasswords().collectAsState(initial = emptyList())
    val coroutinesScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                items = listOf(
                    FabButtonItem(
                        iconRes = Icons.Filled.Settings,
                        label = "Settings"
                    ),
                    FabButtonItem(
                        iconRes = Icons.Filled.Search,
                        label = "Search"
                    ),
                    FabButtonItem(
                        iconRes = Icons.Filled.Add,
                        label = "Add Password",
                    )
                ),
                onFabItemClicked = {
                    when (it.label) {
                        "Search" -> {
                            isBottomAppBarRaised = !isBottomAppBarRaised
                            bottomAppBarHeight = if (isBottomAppBarRaised) 65.dp else 0.dp
                        }

                        "Add Password" -> {
                            coroutinesScope.launch {
                                mainDb.dao.insertPassword(
                                    Password(login = "", service = "")
                                )
                            }
                        }
                    }
                },
                fabIcon = FabButtonMain(),
                fabOption = FabButtonSub(),
            )
        },

        bottomBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(bottomAppBarHeight)
                    .padding(4.dp),
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {},
                ) {

            }
        },

        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                items(passwordStateList.value) {
                    PasswordItem()
                }
            }
        }
    )
}

class SaveDialogViewModel : ViewModel() {
    var title: String by mutableStateOf("")
        private set

    fun updateTextField1(newText: String) {
        title = newText
    }
}
