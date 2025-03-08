package com.example.currencyconverter.data.remote

import com.example.currencyconverter.data.model.ResponseCurrenciesDescription
import com.example.currencyconverter.data.model.ResponseFlags
import com.example.currencyconverter.data.model.ResponseTodayRates
import com.example.currencyconverter.data.model.ResponseYesterdayRates
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance{
    private const val BASE_URL_CURRENCY = "https://api.freecurrencyapi.com/"
    private const val BASE_URL_FLAGS = "https://gist.githubusercontent.com/ibrahimhajjaj/a0e39e7330aebf0feb49912f1bf9062f/raw/d160e7d3b0e11ea3912e97a1b3b25b359746c86a/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_CURRENCY)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val currencyApi: ApiService by lazy {
        retrofit.newBuilder().baseUrl(BASE_URL_CURRENCY).build().create(ApiService::class.java)
    }

    val flagApi: ApiFlagService by lazy {
        retrofit.newBuilder().baseUrl(BASE_URL_FLAGS).build().create(ApiFlagService::class.java)
    }
}

interface ApiService {
    @GET("v1/latest")
    suspend fun getExchangeRates(
        @Query("apikey") apiKey: String = API_KEY,
    ): ResponseTodayRates

    @GET("v1/currencies")
    suspend fun getCurrencies(
        @Query("apikey") apiKey: String = API_KEY,
    ) : ResponseCurrenciesDescription

    @GET("v1/historical")
    suspend fun getYesterdayRates(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("date") date: String
    ) : ResponseYesterdayRates
}

interface ApiFlagService {
    @GET("currencies-with-flags.json")
    suspend fun getFlags() : ResponseFlags
}
