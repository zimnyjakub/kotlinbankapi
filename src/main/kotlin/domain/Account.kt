package domain

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id


public class Account(val id: Int, var currency: Currency) {
    var balance: Money = Money(0, currency)

    fun deposit(money: Money) {
        if (money.currency != currency)
            throw Exception("This account does not support this currency") //todo domain exception
        balance += money
    }

    fun deposit(amount: Int) {
        deposit(Money(amount, currency))
    }


    fun withdraw(money: Money) {
        if (money.currency != currency)
            throw Exception("This account does not support this currency") //todo domain exception

        if (money > balance) {
            throw Exception("Not enough money to withdraw") //todo domain exception
        }

        balance -= money
    }

    fun withdraw(amount: Int) {
        withdraw(Money(amount, currency))
    }



    fun transferMoneyToAccount(account: Account, amount: Money) {
        if (account.currency != currency)
            throw Exception("Currencies are not compatibile, Please exchange money first") //todo domain exception

        if (amount > balance)
            throw Exception("You dont have enough money to transfer money") //todo domain exception

        withdraw(amount)
        account.deposit(amount)
    }

    fun transferMoneyToAccount(account: Account, amount: Int) {
        transferMoneyToAccount(account, Money(amount, currency))
    }

    fun changeCurrencyTo(currency: Currency) {
        //todo try to exchange all currency first!!
        this.currency = currency
    }

    override fun equals(other: Any?): Boolean {
        return when {
            other == null -> false
            other !is Account -> false
            other.id == id -> true

            else -> false
        }
    }

    override fun hashCode(): Int {
        var hash = 37
        hash = hash * 23 + id.hashCode()
        return hash
    }

}


