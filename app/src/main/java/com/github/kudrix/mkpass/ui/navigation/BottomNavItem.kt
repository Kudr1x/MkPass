package com.github.kudrix.mkpass.ui.navigation

import com.github.kudrix.mkpass.R

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    data object DoubleFA : BottomNavItem("2FAScreen", R.drawable.shield, "2FA")
    data object PasswordManager : BottomNavItem("ManagerScreen", R.drawable.key, "Passwords")
    data object Settings : BottomNavItem("SettingsScreen", R.drawable.settings, "Settings")
}