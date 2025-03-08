package com.example.currencyconverter.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.data.model.CurrencyData

@Composable
fun CurrencySelectorButton(data: CurrencyData, onSelected: (CurrencyData) -> Unit, dataState: MainViewModel.MainScreenDataState) {

    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                showDialog = true
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Flag(data, 30.dp)
        Spacer(Modifier.width(10.dp))
        Text(
            text = data.description?.code ?: "err",
            modifier = Modifier.widthIn(min = 40.dp),
            textAlign = TextAlign.Center
        )
        Icon(Icons.Default.ArrowDropDown, null)

        if (showDialog) {
            SelectCurrencyDialog(
                dataState = dataState,
                selectedCurrency = data,
                onSelected = {data->
                    onSelected(data)
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }
    }
}