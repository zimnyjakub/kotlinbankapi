package unit

import domain.Account
import domain.Currency
import domain.Money
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.junit.jupiter.api.Assertions

class AccountTest {

    @Test
    fun testIfNewAccountHasEmptyBalance() {
        val acc = Account(123, Currency.PLN())

        Assert.assertTrue(acc.balance == Money(0, acc.currency))
    }

    @Test
    fun testIfCanDepositSomeMoney() {
        val acc = Account(123, Currency.PLN())

        acc.deposit(Money(100, Currency.PLN()))

        Assert.assertTrue(acc.balance == Money(100, acc.currency))
    }

    @Test
    fun testIfDepositMoneyWithDifferentCurrencyThrowsAnException() {
        val acc = Account(123, Currency.PLN())


        Assertions.assertThrows(Exception::class.java) {
            acc.deposit(Money(100, Currency.USD()))
        }
    }

    @Test
    fun testIfCanWithdrawSomeMoney() {
        val acc = Account(123, Currency.PLN())

        acc.deposit(Money(100, Currency.PLN()))
        acc.withdraw(Money(50, Currency.PLN()))

        Assert.assertTrue(acc.balance == Money(50, acc.currency))
    }


    @Test
    fun testIfCanTransferMoney() {
        val acc1 = Account(123, Currency.PLN())
        val acc2 = Account(124, Currency.PLN())

        acc1.deposit(Money(100, Currency.PLN()))
        acc1.transferMoneyToAccount(acc2, Money(50, Currency.PLN()))

        Assert.assertTrue(acc1.balance == Money(50, acc1.currency))
        Assert.assertTrue(acc2.balance == Money(50, acc2.currency))
    }

    @Test
    fun testIfTransferingMoneyToIncompatibileCurrencyThrowsAnException() {
        val acc1 = Account(123, Currency.PLN())
        val acc2 = Account(124, Currency.EUR())

        acc1.deposit(Money(100, Currency.PLN()))

        Assertions.assertThrows(Exception::class.java) {
            acc1.transferMoneyToAccount(acc2, Money(50, Currency.PLN()))
        }

    }

    @Test
    fun testIfTransferingmoreMoneyThanYouHaveThrowsAnException() {
        val acc1 = Account(123, Currency.PLN())
        val acc2 = Account(124, Currency.EUR())

        acc1.deposit(Money(100, Currency.PLN()))

        Assertions.assertThrows(Exception::class.java) {
            acc1.transferMoneyToAccount(acc2, Money(120, Currency.PLN()))
        }

    }

    @Test
    fun testEqualityWithTwoEqualAccountsShouldReturnTrue() {
        val acc1 = Account(123, Currency.PLN())
        val acc2 = Account(123, Currency.EUR())


        Assert.assertTrue(acc1 == acc2)
    }

    @Test
    fun testEqualityWithDifferentIdsShouldReturnFalse() {
        val acc1 = Account(123, Currency.PLN())
        val acc2 = Account(124, Currency.EUR())


        Assert.assertFalse(acc1 == acc2)
    }

    @Test
    @Ignore("not yet implemented")
    fun testIfChangingTheCurrencyCorrectsTheBalnce() {
        val acc1 = Account(123, Currency.PLN())

        acc1.deposit(100)
        acc1.changeCurrencyTo(Currency.EUR()) //exchange in signature?
        acc1.balance //TODO should be equal to some number

    }
}