package com.example.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyDescription(
    val symbol: String,
    val name: String,
    @SerializedName("symbol_native") val symbolNative: String,
    @SerializedName("decimal_digits") val decimalDigits: Long,
    val rounding: Long,
    val code: String,
    @SerializedName("name_plural") val namePlural: String,
    val type: String,
)

