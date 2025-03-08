package com.example.currencyconverter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currencyconverter.data.model.CurrencyData
import java.util.Locale
import kotlin.math.abs

@Composable
fun CurrencyRateCard(selectedData: CurrencyData, otherData: CurrencyData, modifier: Modifier = Modifier){

    Row(
        modifier = modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FlagPair(selectedData, otherData)

        val rate = CurrencyData.calculateRate(selectedData.value, otherData.value)
        val yesterdayRate= CurrencyData.calculateRate(selectedData.yesterdayValue, otherData.yesterdayValue)

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)){
                    append("${selectedData.description?.code}/${otherData.description?.code}\n")
                }
                withStyle(SpanStyle(fontSize = 13.sp)){
                    (otherData.value ?: 1).toDouble() / 5
                    append("1 ${selectedData.description?.code} = ${String.format(Locale.getDefault(),"%.${(otherData.description?.decimalDigits ?: 2)}f", rate)
                    } ${otherData.description?.code}")
                }
            },
            lineHeight = 16.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            var tint by remember { mutableStateOf(Color.Black) }
            var drawable by remember { mutableStateOf(R.drawable.ic_trend_flat_24) }
            val diff = yesterdayRate - rate
            if (diff <= -0.005){ // GROWTH
                tint = Color.Green
                drawable = R.drawable.ic_trend_up_24
            } else if (diff >= 0.005){ // LOST
                tint = Color.Red
                drawable = R.drawable.ic_trend_down_24
            } else {
                tint = Color.Gray
                drawable = R.drawable.ic_trend_flat_24
            }
            Icon(painterResource(drawable), null, tint = tint)
            Text(String.format(Locale.getDefault(),"%.2f", abs(diff)), color = tint)
        }

    }
}