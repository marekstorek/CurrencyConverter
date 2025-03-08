package com.example.currencyconverter.data.model

import java.util.UUID

data class ConverterItem(
    var currencyData: CurrencyData,
    val value: String,
    val id: String = UUID.randomUUID().toString()
)

