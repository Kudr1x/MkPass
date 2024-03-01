package com.github.kudrix.mkpass

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.ui.screens.LoginScreen
import com.github.kudrix.mkpass.ui.screens.ManagerScreen
import com.github.kudrix.mkpass.ui.theme.MkPassTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainDb: MainDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
            val navController = rememberNavController()
            MkPassTheme {
                NavHost(
                    navController = navController,
                    startDestination = "LoginScreen"
                ) {
                    composable("LoginScreen") {
                        LoginScreen(navController)
                    }

                    composable("ManagerScreen") {
                        ManagerScreen(mainDb)
                    }
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }
}

