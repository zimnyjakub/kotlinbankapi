package domain

import kotlin.math.roundToInt

data class Money(val Amount: Int, val Currency: Currency) {
    operator fun plus(other: Money): Money {
        if(Currency != other.Currency) {
            throw Exception("Convert currency first") //todo domain exception
        }
        return Money(Amount + other.Amount, Currency)
    }

    operator fun minus(other: Money): Money {
        if(other.Amount > Amount)
            throw Exception("domain.Money cannot have negative value") //todo domain exception

        if(Currency != other.Currency)
            throw Exception("Convert currency first") //todo domain exception

        return Money(Amount - other.Amount, Currency)

    }

    operator fun times(multiplier: Float): Money {
        return Money(
            (Amount * multiplier).roundToInt(),
            Currency
        ) //todo rounding can lose money, think of something better
    }

    operator fun compareTo(other: Money): Int {
        return when {
            other.Amount < Amount -> 1
            other.Amount > Amount -> -1
            other.Amount == Amount -> 0
            else -> throw Exception("Invalid operation") //todo ??
        }
    }

    fun equals(other: Money): Boolean {
        return when {
            other.Currency != Currency -> false
            other.Amount != Amount -> false
            else -> true
        }
    }


}