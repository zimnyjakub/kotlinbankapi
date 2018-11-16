package domain

import kotlin.math.roundToInt

data class Money(val amount: Int, val currency: Currency) {
    operator fun plus(other: Money): Money {
        if(currency != other.currency) {
            throw Exception("Convert currency first") //todo domain exception
        }
        return Money(amount + other.amount, currency)
    }

    operator fun minus(other: Money): Money {
        if(other.amount > amount)
            throw Exception("domain.Money cannot have negative value") //todo domain exception

        if(currency != other.currency)
            throw Exception("Convert currency first") //todo domain exception

        return Money(amount - other.amount, currency)

    }

    operator fun times(multiplier: Float): Money {
        return Money(
            (amount * multiplier).roundToInt(),
            currency
        ) //todo rounding can lose money, think of something better
    }

    operator fun compareTo(other: Money): Int {
        return when {
            other.amount < amount -> 1
            other.amount > amount -> -1
            other.amount == amount -> 0
            else -> throw Exception("Invalid operation") //todo ??
        }
    }

    fun equals(other: Money): Boolean {
        return when {
            other.currency != currency -> false
            other.amount != amount -> false
            else -> true
        }
    }


}