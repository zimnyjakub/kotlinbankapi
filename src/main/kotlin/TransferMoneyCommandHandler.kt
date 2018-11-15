import io.vertx.core.Handler
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject

class TransferMoneyCommandHandler : Handler<Message<JsonObject>> {
    override fun handle(event: Message<JsonObject>) {
        val command = event.body()
        println("received message ${command.getValue("fromAccount")} ${command.getValue("toAccount")} ${command.getValue("moneyAmount")}")
    }

}
