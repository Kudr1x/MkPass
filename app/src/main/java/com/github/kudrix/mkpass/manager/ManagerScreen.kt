package com.github.kudrix.mkpass.manager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.kudrix.mkpass.R
import com.github.kudrix.mkpass.ui.items.PasswordItem
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ManagerScreen() {
    var isExpanded by remember { mutableStateOf(true) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),


        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                scrollBehavior = scrollBehavior,
                actions = {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        query = "",
                        onQueryChange = {},
                        onSearch = {},
                        active = false,
                        onActiveChange = {}
                    ) {

                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(
                        onClick = { /* do something */ }
                    ) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Localized description"
                        )
                    }

                    IconButton(
                        onClick = { /* do something */ }
                    ) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = "Localized description"
                        )
                    }

                    IconButton(
                        onClick = { /* do something */ }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.filter),
                            contentDescription = "Localized description"
                        )
                    }
                },

                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add password")
                    }
                }
            )
        },

        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(15) {
                    PasswordItem()
                }
            }
        }
    )
}
