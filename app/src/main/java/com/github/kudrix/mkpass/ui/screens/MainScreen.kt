package com.github.kudrix.mkpass.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.ui.navigation.BottomNavigationBar
import com.github.kudrix.mkpass.ui.navigation.NavGraph

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
        NavGraph(navController, it, mainDb)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}