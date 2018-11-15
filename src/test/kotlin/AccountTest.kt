import domain.Account
import domain.Currency
import domain.Money
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class AccountTest {

    @Test
    fun testIfNewAccountHasEmptyBalance() {
        val acc: Account = Account(123, Currency.PLN())

        Assert.assertTrue(acc.balance == Money(0, acc.Currency))
    }

    @Test
    fun testIfCanDepositSomeMoney() {
        val acc: Account = Account(123, Currency.PLN())

        acc.deposit(Money(100, Currency.PLN()))

        Assert.assertTrue(acc.balance == Money(100, acc.Currency))
    }

    @Test
    fun testIfDepositMoneyWithDifferentCurrencyThrowsAnException() {
        val acc: Account = Account(123, Currency.PLN())


        Assertions.assertThrows(Exception::class.java) {
            acc.deposit(Money(100, Currency.USD()))
        }
    }

    @Test
    fun testIfCanWithdrawSomeMoney() {
        val acc: Account = Account(123, Currency.PLN())

        acc.deposit(Money(100, Currency.PLN()))
        acc.withdraw(Money(50, Currency.PLN()))

        Assert.assertTrue(acc.balance == Money(50, acc.Currency))
    }


    @Test
    fun testIfCanTransferMoney() {
        val acc1: Account = Account(123, Currency.PLN())
        val acc2: Account = Account(124, Currency.PLN())

        acc1.deposit(Money(100, Currency.PLN()))
        acc1.transferMoneyToAccount(acc2, Money(50, Currency.PLN()))

        Assert.assertTrue(acc1.balance == Money(50, acc1.Currency))
        Assert.assertTrue(acc2.balance == Money(50, acc2.Currency))
    }

    @Test
    fun testIfTransferingMoneyToIncompatibileCurrencyThrowsAnException() {
        val acc1: Account = Account(123, Currency.PLN())
        val acc2: Account = Account(124, Currency.EUR())

        acc1.deposit(Money(100, Currency.PLN()))

        Assertions.assertThrows(Exception::class.java) {
            acc1.transferMoneyToAccount(acc2, Money(50, Currency.PLN()))
        }

    }

    @Test
    fun testIfTransferingmoreMoneyThanYouHaveThrowsAnException() {
        val acc1: Account = Account(123, Currency.PLN())
        val acc2: Account = Account(124, Currency.EUR())

        acc1.deposit(Money(100, Currency.PLN()))

        Assertions.assertThrows(Exception::class.java) {
            acc1.transferMoneyToAccount(acc2, Money(120, Currency.PLN()))
        }

    }
}