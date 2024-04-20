package com.github.kudrix.mkpass.ui.screens

<<<<<<< Updated upstream
import android.widget.Toast
=======
>>>>>>> Stashed changes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
<<<<<<< Updated upstream
import androidx.compose.foundation.lazy.LazyColumn
=======
>>>>>>> Stashed changes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
<<<<<<< Updated upstream
import androidx.compose.runtime.mutableIntStateOf
=======
>>>>>>> Stashed changes
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kudrix.mkpass.util.settings.DataStoreManager
import com.github.kudrix.mkpass.util.settings.SettingsData
<<<<<<< Updated upstream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
=======
>>>>>>> Stashed changes
import kotlinx.coroutines.launch

@Preview
@Composable
fun SettingsScreen(){
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)
    val coroutine = rememberCoroutineScope()

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    val settingsState = dataStoreManager.getSettings().collectAsState(initial = SettingsData())
    var sliderPosition by remember { mutableFloatStateOf(settingsState.value.passwordLen.toFloat()) }

    Scaffold (

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = "Password settings",
                    style = MaterialTheme.typography.headlineSmall
                )

                Column {
                    Text(
                        modifier = Modifier
<<<<<<< Updated upstream
                            .padding(start = 20.dp),
=======
                            .padding(
                                start = 20.dp
                            ),
>>>>>>> Stashed changes
                        text = "Password length settings",
                        style = MaterialTheme.typography.bodySmall
                    )

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Slider(
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .fillMaxWidth(0.85f),
                            value = settingsState.value.passwordLen.toFloat(),
                            onValueChange = {
                                coroutine.launch {
                                    dataStoreManager.saveLen(it.toInt())
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
                            text = settingsState.value.passwordLen.toFloat().toString()
                        )
                    }
                }
            }
        }
    )
}