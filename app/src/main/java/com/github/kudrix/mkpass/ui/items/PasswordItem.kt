package com.github.kudrix.mkpass.ui.items

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.kudrix.mkpass.R
<<<<<<< Updated upstream
import com.github.kudrix.mkpass.data.MainDb
import com.github.kudrix.mkpass.data.Password
=======
import com.github.kudrix.mkpass.di.MainDb
import com.github.kudrix.mkpass.room.Password
import com.github.kudrix.mkpass.ui.models.ManagerViewModel
>>>>>>> Stashed changes
import com.github.kudrix.mkpass.util.settings.DataStoreManager
import com.github.kudrix.mkpass.util.settings.SettingsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
<<<<<<< Updated upstream
fun PasswordItem(password: Password, mainDb: MainDb){
=======
fun PasswordItem(
    password: Password,
    passwordViewModel: PasswordItemViewModel,
    showDialog: (Password)-> Unit,
){
>>>>>>> Stashed changes
    val clipboard = LocalClipboardManager.current
    val context = LocalContext.current

    val dataStoreManager = DataStoreManager(context)
    val settingsState = dataStoreManager.getSettings().collectAsState(initial = SettingsData())

<<<<<<< Updated upstream
    val passwordViewModel = PasswordItemViewModel(mainDb, password)

    var isExpanded by remember { mutableStateOf(false)}
=======
>>>>>>> Stashed changes
    var isLabel by remember { mutableStateOf(false)}
    var isExpanded by remember { mutableStateOf(false)}


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .animateContentSize()
            .height(if (isExpanded) 200.dp else 100.dp),
        onClick = {
            isExpanded = !isExpanded
        },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxHeight(0.3f)
                    .alpha(if (isExpanded or isLabel) 1f else 0f)
                    .padding(
                        start = if (isExpanded) 36.dp else 12.dp,
                        top = 12.dp
                    ),
                text = "label",
                style = MaterialTheme.typography.titleSmall,
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .alpha(if (isExpanded) 1f else 0f),
                painter = painterResource(id = R.drawable.label),
                contentDescription = "Label"
            )

            FilledIconToggleButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp),
                checked = isExpanded,
                onCheckedChange = {
                    isExpanded = it
                }
            ) {
                Icon(
                    if(isExpanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown,
                    contentDescription =
                    if(isExpanded)
                        "UpArrow"
                    else
                        "DownArrow"
                )
            }

            Text(
                modifier = Modifier
                    .align(
                        if (isExpanded or isLabel)
                            Alignment.CenterStart
                        else
                            Alignment.TopStart
                    )
                    .padding(12.dp),
                text = password.service,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
                text = password.login,
                style = MaterialTheme.typography.titleMedium
            )

            FilledIconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp),
                onClick = {
                    passwordViewModel.copyPassword(context, password, clipboard, settingsState)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.copy),
                    contentDescription = "Copy Password"
                )
            }
        }

        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ){

            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(0.9f)
                    .align(Alignment.TopCenter)
                    .background(MaterialTheme.colorScheme.outline)
            )

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .height(100.dp)
                    .padding(16.dp)
            ){
                Text(
                    text = "Version:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(8.dp)
                )

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),

                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    value = passwordViewModel.version,
                    onValueChange = {
                        CoroutineScope(Dispatchers.IO).launch {
                            passwordViewModel.changeVersion(it)
                        }
                    }
                )

                Column{
                    IconButton(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                passwordViewModel.incrementVersion()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Plus"
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )

                    IconButton(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                passwordViewModel.decrementVersion()
                            }
                        }
                    ) {
                       Icon(
                           painter = painterResource(id = R.drawable.remove),
                           contentDescription = "Minus"
                       )
                    }
                }

                IconButton(
                    modifier = Modifier
                        .padding(16.dp),
                    onClick = {
<<<<<<< Updated upstream
                        passwordViewModel.deletePassword(password)
=======
                        managerViewModel.updateDeleteDialogState()
                        showDialog(password)
>>>>>>> Stashed changes
                    }
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete",
                    )
                }
            }
        }
    }
}