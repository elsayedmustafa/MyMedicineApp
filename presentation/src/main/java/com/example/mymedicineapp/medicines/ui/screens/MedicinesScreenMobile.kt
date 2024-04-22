package com.example.mymedicineapp.medicines.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymedicineapp.R
import com.example.mymedicineapp.medicine.model.MedicineDomainModel

@Composable
fun MedicinesScreenMobile(userName:String , medicineList: List<MedicineDomainModel>, onShowBottomSheet: (MedicineDomainModel) -> Unit) {

        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {

            TextUserName(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 35.dp, bottom = 20.dp),
                username = userName,

            )


            if (medicineList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 8.dp)
                        .fillMaxWidth()
                ) {
                    itemsIndexed(medicineList, key = { index, item -> item.medicineName+index }) { index, item ->
                        MedicineItemMobile(item) {
                            onShowBottomSheet(it)
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 8.dp)
                        .fillMaxWidth()
                ) {
                    NoMedicinesContainer()
                }
            }

        }

}

@Composable
fun MedicineItemMobile(medicineItem: MedicineDomainModel, onItemClicked: (medicineItem: MedicineDomainModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(contentColor = Color.White , containerColor = colorResource(id = R.color.placeholder_text_color ), disabledContainerColor = Color.White , disabledContentColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
                .clickable {
                    onItemClicked(medicineItem)
                }
        ) {

            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = stringResource(id = R.string.medicine_name).plus(" :  ").plus(medicineItem.medicineName),
                fontSize = 16.sp,
                color = colorResource(id = R.color.primary_text_color), textAlign = TextAlign.Start,
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = stringResource(id = R.string.medicine_dose).plus(" :  ").plus(medicineItem.medicineDose),
                fontSize = 16.sp,
                color = colorResource(id = R.color.primary_text_color), textAlign = TextAlign.Start,
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = stringResource(id = R.string.medicine_strength).plus(" :  ").plus(medicineItem.medicineStrength),
                fontSize = 16.sp,
                color = colorResource(id = R.color.primary_text_color), textAlign = TextAlign.Start,
            )


        }
    }
}

@Composable
fun NoMedicinesContainer() {

    Box {
        Text(text = stringResource(R.string.no_medicines),
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
            )
    }
}
