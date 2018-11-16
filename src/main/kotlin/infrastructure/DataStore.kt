package infrastructure

interface DataStore<T> {
    fun getById(id: Int) : T?
    fun saveOrUpdate(entity: T)
    fun saveOrUpdate(vararg entities: T)


}