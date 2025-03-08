package com.example.currencyconverter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.ui.components.ConverterItemsCard

@Composable
fun ConvertScreen(viewModel: MainViewModel){
    Box {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                ConverterItemsCard(viewModel = viewModel)
                
                ActionButtonRow(
                    onRemove = {
                        viewModel.removeConvertCurrency()
                    },
                    onAdd = {
                        viewModel.addConvertCurrency()
                    }
                )
            }

    }
}

@Composable
private fun ActionButtonRow(onRemove: () -> Unit, onAdd: () -> Unit){
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
        Button(
            modifier = Modifier.weight(1f),
            onClick = onRemove
        ) {
            Text("Remove")
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = onAdd
        ) {
            Text("Add")
        }
    }
}

