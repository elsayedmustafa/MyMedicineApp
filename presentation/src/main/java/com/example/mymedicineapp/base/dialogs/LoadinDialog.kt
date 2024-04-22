package com.example.mymedicineapp.base.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mymedicineapp.R

@Composable
fun LoadingDialog(loadingState: Boolean) {

    if (loadingState) {
        Dialog(
            onDismissRequest = {},
            content = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 100.dp, minHeight = 80.dp)
                        .padding(horizontal = 18.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            color = colorResource(R.color.primary_french_raspberry),
                        )
                        Text(
                            modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),
                            text = stringResource(id = R.string.loading),

                            fontSize = 18.sp, color = Color.White
                        )
                    }
                }
            }
        )
    }
}
