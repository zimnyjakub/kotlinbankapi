package domain

import infrastructure.DataStore

class InMemoryAccountDataStore private constructor(private val accountList: ArrayList<Account> = ArrayList()) : DataStore<Account> {
    fun clear() = accountList.clear()

    fun numberOfAccounts() : Int = accountList.size

    private object Holder { val INSTANCE = InMemoryAccountDataStore() }

    companion object {
        val instance: InMemoryAccountDataStore by lazy { Holder.INSTANCE }
    }

    override fun getById(id: Int): Account? {
        return accountList.firstOrNull { it.id == id }
    }

    override fun saveOrUpdate(entity: Account) {
        if(accountList.contains(entity)) {
            val entityIndex = accountList.indexOf(entity)
            accountList[entityIndex] = entity
        } else
            accountList.add(entity)

    }

    override fun saveOrUpdate(vararg entities: Account) {
        for(entity in entities)
            saveOrUpdate(entity)
    }
}