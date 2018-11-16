import domain.Account
import domain.Currency
import domain.InMemoryAccountDataStore
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class InMemoryAccountDataStoreTests {

    @Before
    fun cleanDataStore() {
        InMemoryAccountDataStore.instance.clear()
    }

    @Test
    fun testIfCanAddToDataStore() {
        val dataStore = InMemoryAccountDataStore.instance

        val acc1 = Account(1, Currency.PLN())
        dataStore.saveOrUpdate(acc1)

        val acc2 = dataStore.getById(acc1.id)

        Assert.assertEquals(acc1, acc2)

        Assert.assertEquals(dataStore.numberOfAccounts(), 1)

    }

    @Test
    fun testIfSaveOrUpdateChangesTheEntity() {
        val dataStore = InMemoryAccountDataStore.instance

        val acc1 = Account(1, Currency.PLN())
        dataStore.saveOrUpdate(acc1)

        acc1.changeCurrencyTo(Currency.EUR())
        dataStore.saveOrUpdate(acc1)


        val acc2 = dataStore.getById(acc1.id)
        Assert.assertEquals(acc1, acc2)

        Assert.assertEquals(acc2!!.currency, Currency.EUR())

        Assert.assertEquals(dataStore.numberOfAccounts(), 1)
    }

    @Test
    fun testIfCanAddMoreThanOneAccountToList() {
        val dataStore = InMemoryAccountDataStore.instance

        val acc1 = Account(1, Currency.PLN())
        dataStore.saveOrUpdate(acc1)

        val acc2 = Account(2, Currency.PLN())
        dataStore.saveOrUpdate(acc2)

        Assert.assertEquals(dataStore.numberOfAccounts(), 2)

    }

    @Test
    fun testIfVarArgsActuallySavesAllOfTheAccounts() {
        val dataStore = InMemoryAccountDataStore.instance

        dataStore.saveOrUpdate(
            Account(1, Currency.PLN()),
            Account(2, Currency.PLN()),
            Account(3, Currency.PLN()),
            Account(4, Currency.EUR()),
            Account(5, Currency.EUR()),
            Account(6, Currency.EUR()),
            Account(7, Currency.PLN()),
            Account(8, Currency.PLN())
        )

        Assert.assertEquals(dataStore.numberOfAccounts(), 8)
    }
}
