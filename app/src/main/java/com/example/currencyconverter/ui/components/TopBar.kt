package com.example.currencyconverter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.ui.theme.LocalExtraColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String){
    Column {
        TopAppBar(
            title = {
                Text(title)
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = LocalExtraColors.current.topBar
            )
        )
        HorizontalDivider(thickness = 0.2.dp)
    }
}