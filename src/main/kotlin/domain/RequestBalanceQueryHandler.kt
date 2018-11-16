package domain

import infrastructure.DataStore
import infrastructure.MessageCodes
import io.vertx.core.json.JsonObject
import io.vertx.core.Handler
import io.vertx.core.eventbus.Message

class RequestBalanceQueryHandler : Handler<Message<JsonObject>> {

    override fun handle(event: Message<JsonObject>) {
        val query = event.body()
        val accountId = (query.getValue("accountId") as String).toInt()

        val repository : DataStore<Account> = InMemoryAccountDataStore.instance
        val account = repository.getById(accountId)

        if (account == null) {
            event.fail(MessageCodes.NOT_FOUND.value, "Account not found")
            return
        }

        event.reply(JsonObject.mapFrom(account.balance))

    }
}
