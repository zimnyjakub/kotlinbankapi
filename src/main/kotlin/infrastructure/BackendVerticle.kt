package infrastructure

import domain.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.JsonObject


class BackendVerticle : AbstractVerticle() {
    lateinit var eventBus: EventBus

    override fun start(startFuture: Future<Void>) {
        initialData()

        eventBus = vertx.eventBus()

        eventBus.consumer<JsonObject>(TRANSFER_MONEY, TransferMoneyCommandHandler())
        eventBus.consumer<JsonObject>(QUERY_BALANCE, RequestBalanceQueryHandler())

    }

    private fun initialData() {
        val dataStore = InMemoryAccountDataStore.instance

        for (id in 1..10000) {
            val acc = Account(id, Currency.PLN())
            acc.deposit(1000)
            dataStore.saveOrUpdate(acc)
        }

//        dataStore.sav
    }

}

//        val config = JsonObject()
//            .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
//            .put("driver_class", "org.hsqldb.jdbcDriver")
//            .put("max_pool_size", 30)
//
//        val client = JDBCClient.createShared(vertx, config)

//    }

//    private fun createSomeData(
//        result: AsyncResult<SQLConnection>,
//        next: Handler<AsyncResult<Void>>, fut: Future<Void>
//    ) {
//        if (result.failed()) {
//            fut.fail(result.cause())
//        } else {
//            val connection = result.result()
//            connection.execute(
//                "CREATE TABLE IF NOT EXISTS Accounts (id INTEGER IDENTITY, name varchar(100), " + "Balance integer, currency varchar(3))"
//            ) {ar ->
//                if (ar.failed()) {
//                    fut.fail(ar.cause())
//                    connection.close()
//                }
//                connection.query("SELECT * FROM Accounts")
//                { select ->
//                    if (select.failed()) {
//                        fut.fail(ar.cause())
//                        connection.close()
//                        return@query
//                    }
//                    if (select.result().numRows == 0) {
//
//                }
//                else {
//                        next.handle(Future.succeededFuture<Void>())
//                        connection.close()
//                    }
//                }
//            }
//        }


//    }
//
//    private fun insert(account: Account, connection: SQLConnection, next: Handler<AsyncResult<Account>>) {
//        val sql = "INSERT INTO Account (amount, currency) VALUES ?, ?"
//        connection.updateWithParams(
//            sql,
//            JsonArray().add(account.balance).add(account.currency)
//        ) { ar ->
//            if (ar.failed()) {
//                next.handle(Future.failedFuture<Account>(ar.cause()))
//                return@updateWithParams
//            }
//            val result = ar.result()
//
//            val a = Account(result.keys.getInteger(0), Currency.PLN())
//            next.handle(Future.succeededFuture<Account>(a))
//        }
//    }
//}
