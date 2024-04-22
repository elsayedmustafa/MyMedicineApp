package com.example.mymedicineapp.auth.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreenMobile(
loginCallback: (userName:String) -> Unit ) {

    var isUserNameHintFocused by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(top = 35.dp)
                .background(color = Color.White)
        ) {

            TextFieldUserName(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 35.dp, bottom = 20.dp),
                username = username,
                isUserNameHintFocused = isUserNameHintFocused,
                onUserNameChange = {
                    username = it
                },
                onFocusedChange = {
                    isUserNameHintFocused = it
                }
            )

            LoginButton(
                enabled = username.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 24.dp)
            ) {
                loginCallback(username)
            }
        }

}
