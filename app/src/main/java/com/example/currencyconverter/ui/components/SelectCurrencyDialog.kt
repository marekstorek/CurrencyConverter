package com.example.currencyconverter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.data.model.CurrencyData
import com.example.currencyconverter.ui.theme.LocalExtraColors


@Composable
fun SelectCurrencyDialog(
    dataState: MainViewModel.MainScreenDataState,
    selectedCurrency: CurrencyData,
    onSelected: (CurrencyData) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        val currencyList by remember {  mutableStateOf(dataState.currencies.values.toList()) }
        LazyVerticalGrid(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            columns = GridCells.Adaptive(80.dp),
        ) {
            items(currencyList) {

                var borderWidth = 0.2.dp
                var background = Color.Transparent
                var fontWeight = FontWeight.Normal

                if (it == selectedCurrency) { // Allows to highlight currently selected currency
                    borderWidth = 1.5.dp
                    background = LocalExtraColors.current.dialogSelected
                    fontWeight = FontWeight.SemiBold
                }

                Row(
                    modifier = Modifier
                        .border(borderWidth, Color.Gray)
                        .background(background)
                        .clickable {
                            onSelected(it)
                        }
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Flag(it, 30.dp)
                    Spacer(Modifier.width(5.dp))
                    Text(
                        it.description?.code ?: "err",
                        maxLines = 1,
                        fontWeight = fontWeight
                    )
                }
            }
        }
    }
}