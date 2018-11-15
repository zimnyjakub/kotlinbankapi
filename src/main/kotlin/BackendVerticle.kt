import domain.TransferMoneyCommand
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.JsonObject

class BackendVerticle : AbstractVerticle() {
    lateinit var eventBus: EventBus

    override fun start(startFuture: Future<Void>) {
        eventBus = vertx.eventBus()

        eventBus.consumer<JsonObject>(TRANSFER_MONEY, TransferMoneyCommandHandler())
    }
}