package com.github.kudrix.mkpass.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.kudrix.mkpass.R
import com.github.kudrix.mkpass.ui.models.LoginViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(navController: NavController){
    val loginViewModel = viewModel<LoginViewModel>()

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val BIVR = BringIntoViewRequester()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(0.4f),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                painter = painterResource(id = R.drawable.key),
                contentDescription = "Logo",
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.04f))

            Text(
                text = "MkPass",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.04f))

            Text(
                text = "Deterministic Password Manager" + "\n" + "Use it everywhere",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }

        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .onFocusEvent { event ->
                        if (event.isFocused) {
                            coroutineScope.launch {
                                BIVR.bringIntoView()
                            }
                        }
                    },
                trailingIcon = {
                    IconButton(onClick = {
                        loginViewModel.updatePasswordVisibility1()
                    }) {
                        Icon(
                            painter =
                                if (loginViewModel.passwordVisibility1)
                                    painterResource(id = R.drawable.visibility)
                                else
                                    painterResource(id = R.drawable.visibility_off),
                            contentDescription = "Visibility Icon"
                        )
                    }},
                value = loginViewModel.textField1,
                onValueChange = {loginViewModel.updateTextField1(it)},
                singleLine = true,
                label = { Text(text = "Master password") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
                visualTransformation =
                    if (loginViewModel.passwordVisibility1)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.04f))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .onFocusEvent { event ->
                        if (event.isFocused) {
                            coroutineScope.launch {
                                BIVR.bringIntoView()
                            }
                        }
                    },
                trailingIcon = {
                    IconButton(onClick = {
                        loginViewModel.updatePasswordVisibility2()
                    }) {
                        Icon(
                            painter =
                            if (loginViewModel.passwordVisibility2)
                                painterResource(id = R.drawable.visibility)
                            else
                                painterResource(id = R.drawable.visibility_off),
                            contentDescription = "Visibility Icon"
                        )
                    }},
                value = loginViewModel.textField2,
                onValueChange = {loginViewModel.updateTextField2(it)},
                singleLine = true,
                label = { Text(text = "Repeat master password") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
                visualTransformation =
                    if (loginViewModel.passwordVisibility2)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .bringIntoViewRequester(BIVR),
            onClick = {
                loginViewModel.checkPasswords(navController)
            }
        ){
            Text(text = "Next")
        }
    }
}
