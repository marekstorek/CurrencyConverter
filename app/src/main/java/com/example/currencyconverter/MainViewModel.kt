package com.example.currencyconverter

import android.annotation.SuppressLint
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.cache.FlagCache.EU_FLAG
import com.example.currencyconverter.data.model.ConverterItem
import com.example.currencyconverter.data.model.CurrencyData
import com.example.currencyconverter.data.model.ResponseFlags
import com.example.currencyconverter.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainViewModel : ViewModel(){

    private val dataState_ = mutableStateOf(MainScreenDataState())
    val dataState: State<MainScreenDataState> = dataState_

    var topBarTitle = mutableStateOf(Screen.Rates.title)

    var selectedCurrency = mutableStateOf<CurrencyData?>(null)

    private val currencyApi = RetrofitInstance.currencyApi
    private val flagsApi = RetrofitInstance.flagApi

    fun selectCurrency(data: CurrencyData){
        selectedCurrency.value = data
    }

    val converterItems = MutableStateFlow( listOf<ConverterItem>() )

    private var lastUpdatedConverterItemId = ""
    private var lastUpdatedConverterItemValue = ""


    init {
        fetchAllData()
    }

    /**
     * Converts string input to a valid double. Removes all whitespace, minus sign and converts comma to a dot
     * */
    private fun String.parseNumberInput(): Double{
        return this.replace(',','.').replace(" ","").replace("-", "").replace(Regex("[\\s\u00A0\u202F]"), "").toDoubleOrNull()?: 0.toDouble()
    }

    fun updateConvertValues(changedItemId: String, newValue: String){
        val baseValue: Double = newValue.parseNumberInput()
        val baseRate: Double = (converterItems.value.find { it.id == changedItemId })?.currencyData?.value ?: 1.toDouble()

        val updatedItems = converterItems.value.map {item->
            if(item.id == changedItemId){
                item.copy(value = newValue)
            } else {
                val rate = dataState_.value.currencies[item.currencyData.description?.code]?.value ?: return

                val formatter = NumberFormat.getNumberInstance(Locale.getDefault()) // Uses space as separator
                formatter.maximumFractionDigits = 2
                formatter.minimumFractionDigits = 0
                val formatted = formatter.format(((baseValue / baseRate) * rate))

                item.copy(value = formatted)
            }
        }
        lastUpdatedConverterItemId = changedItemId
        lastUpdatedConverterItemValue = newValue
        converterItems.value = updatedItems
    }


    // called when the user changes a currency
    fun updateConvertCurrency(changedItemId: String, currencyData: CurrencyData){
        val updatedItems = converterItems.value.map {
            if (changedItemId == it.id) {
                it.copy(currencyData = currencyData)
            } else {
                it
            }
        }
        converterItems.value = updatedItems
        updateConvertValues(lastUpdatedConverterItemId, lastUpdatedConverterItemValue )
    }

    fun addConvertCurrency(){
        val index = converterItems.value.size
        val currencyData = dataState_.value.currencies.values.toList()[index]
        converterItems.value += ConverterItem(
            currencyData = currencyData,
            value = "1"
        )
        if (index == 0){
            lastUpdatedConverterItemId = converterItems.value.first().id
            lastUpdatedConverterItemValue = "1"
        }
        updateConvertValues(lastUpdatedConverterItemId, lastUpdatedConverterItemValue)
    }

    fun removeConvertCurrency(){
        if (converterItems.value.size > 2) {
            converterItems.value = converterItems.value.dropLast(1)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getYesterdayDateFormat(): String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis -= 2*24*60*60*1000 // Multiplying by two to compensate as the API has one day delay
        return SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
    }

    // As this is simple app with no data persistence, we load everything at the beginning
    private fun fetchAllData(){
        viewModelScope.launch {
            try {
                // At first we do all four API calls
                val responseCurrencies = currencyApi.getCurrencies()
                val responseRates = currencyApi.getExchangeRates()
                val responseYesterdayRates = currencyApi.getYesterdayRates(date = getYesterdayDateFormat())

                val flagsResponse: ResponseFlags? = try {
                    flagsApi.getFlags()
                } catch (e : Exception){
                    null
                }

                // then we combine all the data in to one data variable wrapped by state data class
                val currencyDataMap: MutableMap<String, CurrencyData> = mutableMapOf<String, CurrencyData>()
                responseCurrencies.data.forEach { (key, currencyDescription) ->
                    val currencyData = CurrencyData(
                        description = currencyDescription,
                        value = responseRates.data[key],
                        yesterdayValue = responseYesterdayRates.data.values.first()[key], // response consists of one date, thus we select the first element
                        flagUrl = (flagsResponse?.find{it.code == key})?.flag
                    )
                    if (key == "EUR"){ // Api unfortunately misses EU flag so its added manually
                        currencyData.flagUrl = EU_FLAG
                    }
                    currencyDataMap[key] = currencyData
                }
                dataState_.value = dataState_.value.copy(
                    error = null,
                    currencies = currencyDataMap,
                    loading = false
                )

                selectCurrency(currencyDataMap.values.first()) // sets up default (base) currency
                repeat(2){// adds two converterFields
                    addConvertCurrency()
                }
            } catch (e: Exception){
                dataState_.value = dataState_.value.copy(
                    loading = false,
                    error = "Something went wrong: $e"
                )
            }
        }
    }

    @Immutable @Stable
    data class MainScreenDataState(
        var loading: Boolean = true,
        var currencies: Map<String, CurrencyData> = mapOf(),
        var error: String? = null
    )

}

