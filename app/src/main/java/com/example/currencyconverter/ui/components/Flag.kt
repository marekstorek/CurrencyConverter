package com.example.currencyconverter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.data.cache.FlagCache
import com.example.currencyconverter.data.model.CurrencyData

@Composable
fun Flag(currencyData: CurrencyData, size: Dp = 50.dp){
    Image(
        painter = BitmapPainter(FlagCache.getImageBitmap(currencyData)),
        null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(size),
    )
}

@Composable
fun FlagPair(currencyData1: CurrencyData, currencyData2: CurrencyData){
    Box{
        Column {
            Spacer(Modifier.height(10.dp))
            Flag(currencyData1)
        }
        Row(){
            Spacer(Modifier.width(20.dp))
            Flag(currencyData2)
        }
    }
}