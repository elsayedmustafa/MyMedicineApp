package com.example.mymedicineapp.base.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.text.HtmlCompat
import com.example.mymedicineapp.R
import com.example.mymedicineapp.base.FailureViewState

@Composable
fun ErrorDialog(
    htmlMessage: String,
    shouldDismiss: State<Boolean>,
    failureViewState: FailureViewState = FailureViewState.NoInternetViewState,
    onDismiss: () -> Unit,
    onClicked: () -> Unit = { },
    customConfirmText: String = ""
) {

    val isTablet = LocalConfiguration.current.screenWidthDp >= 600

    if (shouldDismiss.value) {
        Dialog(
            onDismissRequest = { onDismiss() },
            content = {
                Card(
                    modifier = if (isTablet) Modifier
                        .shadow(
                            elevation = 45.dp,
                            spotColor = Color(0x39000000),
                            ambientColor = Color(0x39000000)
                        )
                        .padding(0.dp)
                        .width(450.dp)
                        .background(
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 25.dp, end = 25.dp, bottom = 30.dp)

                    else
                        Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(50.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 30.dp),
                            text = stringResource(id = R.string.warning),
                            fontSize = 24.sp,
                            color = colorResource(id = R.color.primary_raisin_black),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier.padding(vertical = 20.dp, horizontal = 25.dp),
                            fontSize = 14.sp,
                            text = buildAnnotatedString {
                                append(HtmlCompat.fromHtml(htmlMessage, 0).trim())
                            },
                            color = colorResource(id = R.color.primary_raisin_black),
                            textAlign = TextAlign.Center
                        )

                        Button(
                            onClick = {
                                if (failureViewState == FailureViewState.NoInternetViewState) {
                                    onDismiss()
                                } else {
                                    onClicked()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.primary_french_raspberry),
                            ),
                            modifier = if (isTablet)
                                Modifier
                                    .shadow(
                                        elevation = 6.dp,
                                        spotColor = colorResource(id = R.color.primary_french_raspberry),
                                        ambientColor = colorResource(id = R.color.primary_french_raspberry)
                                    )
                                    .padding(0.dp)
                                    .width(249.dp)
                                    .height(55.dp)
                                    .background(
                                        color = colorResource(id = R.color.primary_french_raspberry),
                                        shape = RoundedCornerShape(size = 10.dp)
                                    )
                            else Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp, bottom = 30.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 6.dp),
                                text = if (failureViewState is FailureViewState.SessionTimeOutViewState) stringResource(
                                    id = R.string.login_again
                                )
                                else if (customConfirmText.isEmpty()) stringResource(id = R.string.okay) else customConfirmText,
                                color = Color.White,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }
        )
    }
}
