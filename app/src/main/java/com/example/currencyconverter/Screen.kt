package com.example.currencyconverter

import androidx.annotation.DrawableRes

sealed class Screen (
    val title: String,
    val route: String,
    @DrawableRes val icon: Int
){
    data object Rates: Screen("Rates", "rates", R.drawable.ic_trend_up_24)
    data object Convert: Screen("Convert", "convert", R.drawable.ic_exchange_24)
}