package unit

import domain.Currency
import domain.Money
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.lang.Exception


class MoneyTest {
    @Test
    fun testIfCanCreateAnyMoney() {
        val money: Money = Money(100, Currency.PLN())

        Assert.assertNotNull(money)
    }

    @Test
    fun testIfEqualOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = Money(100, Currency.PLN())

        Assert.assertEquals(money1, money2)
        Assert.assertTrue(money1 == money2)

    }

    @Test
    fun testIfNotEqualsOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = Money(200, Currency.PLN())

        Assert.assertNotEquals(money1, money2)
        Assert.assertTrue(money1 != money2)

    }

    @Test
    fun testIfNotEqualsOperatorWorksAsExpectedWhenThereAreTwoDifferentCurrencies() {
        val money1: Money = Money(100, Currency.USD())
        val money2: Money = Money(200, Currency.PLN())

        Assert.assertNotEquals(money1, money2)
        Assert.assertTrue(money1 != money2)

    }

    @Test
    fun testIfMoreThanOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(200, Currency.PLN())
        val money2: Money = Money(100, Currency.PLN())

        Assert.assertTrue(money1 > money2)

    }

    @Test
    fun testIfLessThanOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = Money(200, Currency.PLN())

        Assert.assertTrue(money1 < money2)

    }

    @Test
    fun testIfMoreThanOrEqualOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = Money(100, Currency.PLN())
        val money3: Money = Money(101, Currency.PLN())

        Assert.assertTrue(money1 >= money2)
        Assert.assertTrue(money3 >= money2)

    }

    @Test
    fun testIfLessThanOrEqualOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = Money(100, Currency.PLN())
        val money3: Money = Money(99, Currency.PLN())

        Assert.assertTrue(money1 <= money2)
        Assert.assertTrue(money3 <= money2)

    }

    @Test
    fun testIfAddOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = Money(100, Currency.PLN())
        val money3: Money = money1 + money2;

        Assert.assertEquals(money3, Money(200, Currency.PLN()))

    }

    @Test
    fun testIfAddOperationFailsWhenAddingDifferentCurrencies() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = Money(100, Currency.USD())

        Assertions.assertThrows(Exception::class.java) {
            money1 + money2;
        }

    }

    @Test
    fun testIfSubtractOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(200, Currency.PLN())
        val money2: Money = Money(250, Currency.PLN())

        Assertions.assertThrows(Exception::class.java) {
            money1 - money2
        };
    }

    @Test
    fun testIfSubtractionOperationFailsWhenSubtractingDifferentCurrencies() {
        val money1: Money = Money(200, Currency.PLN())
        val money2: Money = Money(200, Currency.USD())

        Assertions.assertThrows(Exception::class.java) {
            money1 - money2
        };
    }

    @Test
    fun testIfSubtractingTooMuchMoneyThrowsAnException() {
        val money1: Money = Money(200, Currency.PLN())
        val money2: Money = Money(300, Currency.PLN())

        Assertions.assertThrows(Exception::class.java) {
            money1 - money2
        };

    }

    @Test
    fun testIfMultiplicationOperatorWorksAsExpectedOnMoney() {
        val money1: Money = Money(100, Currency.PLN())
        val money2: Money = money1 * 2f;

        Assert.assertEquals(money2, Money(200, Currency.PLN()))
    }

    //TODO
//    @Test
//    fun testIfAllocateMethodWorksAsExpectedOnMoney() {
//        val money1: domain.Money = domain.Money(100, domain.currency.PLN())
//        val money2: domain.Money = domain.Money(100, domain.currency.PLN())
//
//        Assert.assertEquals(money1, money2)
//        Assert.assertTrue(money1 == money2)
//
//    }

}