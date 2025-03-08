package com.example.currencyconverter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.ui.components.CurrencyRateCard
import com.example.currencyconverter.ui.components.SelectBaseRow

@Composable
fun CurrenciesScreen(viewModel: MainViewModel){

    val dataState by viewModel.dataState

    Column(
        modifier = Modifier.fillMaxSize()
    ){

        val list = dataState.currencies.values.toList()

        val selectedCurrency by viewModel.selectedCurrency
        selectedCurrency?.let { SelectBaseRow(it, viewModel) } ?: Text("ERR")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                //.background(MaterialTheme.colorScheme.background)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(list, key = { it.description!!.code }){
                CurrencyRateCard(selectedCurrency!!, it, modifier = Modifier.animateItem())
                HorizontalDivider(thickness = 0.5.dp)
            }
        }
    }
}

