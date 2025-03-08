package com.example.currencyconverter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.data.model.CurrencyData
import com.example.currencyconverter.ui.theme.LocalExtraColors

@Composable
fun SelectBaseRow(
    selected: CurrencyData,
    viewModel: MainViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LocalExtraColors.current.topBar)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select Base Currency",
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp
        )
        Spacer(Modifier.size(20.dp))
        CurrencySelectorButton(
            data = selected,
            onSelected = {
                viewModel.selectCurrency(it)
            },
            dataState = viewModel.dataState.value
        )

    }
    HorizontalDivider()
}
