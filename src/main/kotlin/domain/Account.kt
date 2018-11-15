package domain

public class Account(val Id: Int, val Currency: Currency) {
    var balance: Money = Money(0, Currency)

    fun deposit(money: Money) {
        if(money.Currency != Currency)
            throw Exception("This account does not support this currency") //todo domain exception
        balance += money;
    }

    fun withdraw(money: Money) {
        if(money.Currency != Currency)
            throw Exception("This account does not support this currency") //todo domain exception

        if (money > balance) {
            throw Exception("Not enough money to withdraw") //todo domain exception
        }

        balance -= money;
    }

    fun transferMoneyToAccount(account: Account, amount: Money) {
        if(account.Currency != Currency)
            throw Exception("Currencies are not compatibile, Please exchange money first") //todo domain exception

        if(amount > balance)
            throw Exception("You dont have enough money to transfer money") //todo domain exception

        withdraw(amount)
        account.deposit(amount)
    }
}


