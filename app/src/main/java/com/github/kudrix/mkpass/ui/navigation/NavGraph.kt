package com.github.kudrix.mkpass.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.kudrix.mkpass.ui.models.PasswordListViewModel
import com.github.kudrix.mkpass.ui.screens.AuthenticationScreen
import com.github.kudrix.mkpass.ui.screens.LoginScreen
import com.github.kudrix.mkpass.ui.screens.ManagerScreen
import com.github.kudrix.mkpass.ui.screens.SettingsScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = "LoginScreen",
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable("LoginScreen") {
            LoginScreen(navHostController)
        }

        composable("ManagerScreen") {
            val passwordListViewModel: PasswordListViewModel = viewModel()
            ManagerScreen(passwordListViewModel = passwordListViewModel)
        }

        composable("SettingsScreen") {
            SettingsScreen()
        }

        composable("2FAScreen") {
            AuthenticationScreen()
        }
    }
}