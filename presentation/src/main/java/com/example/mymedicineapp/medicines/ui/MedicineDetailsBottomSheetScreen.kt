package com.example.mymedicineapp.medicines.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mymedicineapp.R
import com.example.mymedicineapp.medicine.model.MedicineDomainModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailsBottomSheetScreen(
    modalBottomSheetState: SheetState,
    medicineDomainModelClicked:MedicineDomainModel,
    title: String,
    onCloseClicked: () -> Unit

    ) {
    val coroutineScope = rememberCoroutineScope()
    val radius = 28.dp

    ModalBottomSheet(
        onDismissRequest = onCloseClicked,
        sheetState = modalBottomSheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = radius, topEnd = radius),
        ) {
                ConstraintLayout {
                    val (txtLabel, radioGroup, closeBtn) = createRefs()

                    Icon(
                        modifier = Modifier
                            .constrainAs(closeBtn) {
                                top.linkTo(txtLabel.top)
                                bottom.linkTo(txtLabel.bottom)
                                start.linkTo(parent.start, margin = 32.dp)
                            }
                            .clickable {
                                coroutineScope.launch {
                                    modalBottomSheetState.hide()
                                    onCloseClicked()
                                }
                            },
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(txtLabel) {
                                top.linkTo(parent.top, 12.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, 20.dp)
                            },
                        text = title,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.primary_raisin_black),
                    )

                    Column(
                        modifier = Modifier
                            .constrainAs(radioGroup) {
                                top.linkTo(txtLabel.bottom, margin = 10.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom, 16.dp)
                            }
                            .padding(start = 24.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                    ) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = stringResource(id = R.string.medicine_name).plus(" :  ")
                                .plus(medicineDomainModelClicked.medicineName),
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.primary_text_color),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = stringResource(id = R.string.medicine_dose).plus(" :  ")
                                .plus(medicineDomainModelClicked.medicineDose),
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.primary_text_color),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = stringResource(id = R.string.medicine_strength).plus(" :  ")
                                .plus(medicineDomainModelClicked.medicineStrength),
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.primary_text_color),
                            textAlign = TextAlign.Start,
                        )
                    }
                }

    }
}
