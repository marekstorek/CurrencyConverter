package com.example.currencyconverter.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.data.model.ConverterItem
import com.example.currencyconverter.data.model.CurrencyData
import com.example.currencyconverter.ui.theme.LocalExtraColors

@Composable
fun ConverterItemsCard(viewModel: MainViewModel){

    val converterItems by viewModel.converterItems.collectAsState()

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = LocalExtraColors.current.card,
        ),
    ) {
        Column(modifier = Modifier.padding(horizontal = 19.dp).animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioLowBouncy))) {
            Spacer(Modifier.height(15.dp))

            converterItems.forEach {converterItem->
                ConverterItemView(
                    dataState = viewModel.dataState.value,
                    converterItem = converterItem,
                    onValueChange = { newValue ->
                        viewModel.updateConvertValues(converterItem.id, newValue)
                    },
                    onCurrencyChanged = { newData->
                        viewModel.updateConvertCurrency(converterItem.id, newData)
                    })
                Spacer(Modifier.height(15.dp))
                if (converterItem != converterItems.last()){
                    HorizontalDivider()
                    Spacer(Modifier.height(15.dp))
                }
            }
        }
    }
}

@Composable
private fun ConverterItemView(dataState: MainViewModel.MainScreenDataState, converterItem: ConverterItem, onValueChange: (String) -> Unit, onCurrencyChanged: (CurrencyData) -> Unit){

    val focusManager = LocalFocusManager.current

    Row(verticalAlignment = Alignment.CenterVertically){
        BasicTextField(
            value = converterItem.value,
            onValueChange = { onValueChange(it) },
            textStyle = TextStyle(fontSize = 25.sp, color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                },
            ),
        )
        CurrencySelectorButton(
            data = converterItem.currencyData,
            onSelected = {newData ->
                onCurrencyChanged(newData)
            },
            dataState = dataState
        )
    }
}

