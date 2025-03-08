package com.example.currencyconverter.data.model

class CurrencyData(
    var description: CurrencyDescription? = null,
    var flagUrl: String? = null,
    var value: Double? = null,
    var yesterdayValue: Double? = null
) {

    companion object{
        fun calculateRate(baseValue: Double?, otherValue: Double?): Double {
            return (otherValue ?: 1).toDouble() / (baseValue ?: 1).toDouble()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is CurrencyData){
            return this.description?.code == other.description?.code
        } else {
            return super.equals(other)
        }
    }

    override fun hashCode(): Int {
        var result = description?.hashCode() ?: 0
        result = 31 * result + (flagUrl?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (yesterdayValue?.hashCode() ?: 0)
        return result
    }
}

