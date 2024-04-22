package com.example.mymedicineapp.auth.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.mymedicineapp.R
import com.example.mymedicineapp.auth.LoginViewModel
import com.example.mymedicineapp.auth.navhost.MainNavHostDestinations
import com.example.mymedicineapp.base.BaseComposableScreen

@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    var username by remember { mutableStateOf("") }

    LaunchedEffect(loginViewModel.loginState.value) {
        when (loginViewModel.loginState.value) {
            true -> {

              val navOptions = NavOptions.Builder()
              .setPopUpTo(MainNavHostDestinations.LOGIN_SCREEN.value, false ).build()
               navController.navigate("${MainNavHostDestinations.MEDICINES_SCREEN.value}/${username}", navOptions)
                loginViewModel.loginState.value=false
            }
            false -> {
            }
        }
    }

    BaseComposableScreen(navController = navController, viewModel = loginViewModel) {
        LoginScreenMobile{
            username=it
            loginViewModel.login(it)
        }
    }
}

@Composable
fun TextFieldUserName(
    modifier: Modifier,
    username: String,
    isUserNameHintFocused: Boolean,
    onUserNameChange: (newValue: String) -> Unit,
    onFocusedChange: (newValue: Boolean) -> Unit
) {
    OutlinedTextField(
        value = username,
        onValueChange = { onUserNameChange(it) },
        label = {
            Text(
                text = stringResource(id = R.string.user_name),
                style = if ((username.isNotEmpty() || isUserNameHintFocused)) TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = colorResource(id = R.color.hint_text_color),
                    textAlign = TextAlign.Right,
                ) else TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight(400),
                    color = colorResource(id = R.color.placeholder_text_color),
                    textAlign = TextAlign.Right,
                ),
            )
        },
        singleLine = true,
        modifier = modifier
            .onFocusChanged { onFocusedChange(it.isFocused) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.focused_border_color),
            focusedLabelColor = colorResource(id = R.color.placeholder_text_color),
            cursorColor = colorResource(id = R.color.cursor_color),
            focusedTextColor = colorResource(id = R.color.text_field_focused_label_color),
            unfocusedTextColor = colorResource(id = R.color.text_field_focused_label_color),
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(600),
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        )
    )
}

@Composable
fun LoginButton(enabled: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.button_background_color),
            disabledContainerColor = colorResource(id = R.color.button_background_color).copy(
                alpha = .3f
            )
        ),
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = stringResource(id = R.string.login),
            color = colorResource(id = R.color.button_text_color),
            fontSize = 16.sp,
        )
    }
}
