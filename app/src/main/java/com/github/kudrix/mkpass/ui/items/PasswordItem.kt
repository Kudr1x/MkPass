package com.github.kudrix.mkpass.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kudrix.mkpass.R

@Preview(showBackground = true)
@Composable
fun PasswordItem(){

    var isExpanded by remember { mutableStateOf(false)}
    var vers = 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(if (isExpanded) 300.dp else 100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ){
            IconToggleButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp),
                checked = isExpanded,
                onCheckedChange = {newState -> isExpanded = newState}
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
                    .align(Alignment.TopStart)
                    .padding(4.dp),
                text = "test.com",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                text = "Максимка",
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.copy),
                    contentDescription = "Copy Password"
                )
            }
        }

        Box(
            modifier = Modifier
                .height(200.dp)
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
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    value = "",
                    onValueChange = {}
                )

                Column{
                    IconButton(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Plus")
                    }

                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )

                    IconButton(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),
                        onClick = { /*TODO*/ }
                    ) {
                       Icon(
                           painter = painterResource(id = R.drawable.remove),
                           contentDescription = "Minus")
                    }
                }

                IconButton(
                    modifier = Modifier
                        .padding(16.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Deleate",
                    )
                }
            }

            Card(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomStart),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                BasicTextField(
                    value = "",
                    onValueChange = {}
                )
            }
        }
    }
}