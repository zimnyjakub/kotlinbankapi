package domain

import infrastructure.DataStore
import io.vertx.core.Handler
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject

class TransferMoneyCommandHandler : Handler<Message<JsonObject>> {
    private var logger = io.vertx.core.logging.LoggerFactory.getLogger(TransferMoneyCommandHandler::class.java)

    override fun handle(event: Message<JsonObject>) {
        val command = event.body()

        val payerId: Int = (command.getValue("fromAccount") as String).toInt()
        val receiverId: Int = (command.getValue("toAccount") as String).toInt()
        val moneyAmount: Int = (command.getValue("moneyAmount") as String).toInt()

        val repository : DataStore<Account> = InMemoryAccountDataStore.instance //todo dependency injection / real database
        val payer = repository.getById(payerId)
        val receiver = repository.getById(receiverId)

        if(payer == null) {
            logger.error("Payer account with id $payerId not found")
            return
        }
        if(receiver == null) {
            logger.error("Receiver account with id $receiverId not found")
            return
        }

        try {
            payer.transferMoneyToAccount(receiver, moneyAmount)
            //todo save this to event store
        } catch(e: Exception) {
            logger.error("There was an error processing your request: ${e.message}")
        }
    }

}
