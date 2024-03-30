package com.github.kudrix.mkpass.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.ui.navigation.BottomNavigationBar
import com.github.kudrix.mkpass.ui.navigation.NavGraph
import com.github.kudrix.mkpass.util.settings.DataStoreManager
import com.github.kudrix.mkpass.util.settings.SettingsData

@Composable
fun MainScreen(mainDb: MainDb) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if(currentRoute(navController) != "LoginScreen"){
                BottomNavigationBar(navController = navController)
            }
        }
    ){
        NavGraph(mainDb = mainDb, navHostController = navController, it)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}