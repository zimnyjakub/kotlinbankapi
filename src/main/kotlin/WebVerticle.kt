import domain.TransferMoneyCommand
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.eventbus.EventBus
import io.vertx.core.http.HttpServer
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext


class WebVerticle : AbstractVerticle() {
    lateinit var httpServer : HttpServer
    lateinit var eventBus: EventBus

    override fun start(startFuture: Future<Void>) {
        eventBus = vertx.eventBus()
        val router = createRouter()
        httpServer = vertx.createHttpServer()
            .requestHandler {
                router.accept(it)
            }
            .listen(8000) { result ->
                if (result.succeeded()) {
                    startFuture.complete()
                } else {
                    startFuture.fail(result.cause())
                }
            }
    }

    private fun createRouter() = Router.router(vertx).apply {
//        get("/accounts").handler(handlerRoot)
        post("/transfer/:fromAccount/:amount/:toAccount")
            .consumes("application/json")
            .produces("application/json")
            .handler(transferHandler)

    }

//    private val handlerRoot = Handler<RoutingContext> { req ->
//        req.response().end("Welcome!")
//    }

    private val transferHandler = Handler<RoutingContext> {
        val params = it.request().params()
        val from: String = params.get("fromAccount")
        val to: String = params.get("toAccount")
        val amount: String = params.get("amount")

        val command = mapOf("fromAccount" to from, "toAccount" to to, "moneyAmount" to amount)
        eventBus.send(TRANSFER_MONEY, JsonObject(command))
//        eventBus.send(TRANSFER_MONEY, JsonObject.mapFrom(TransferMoneyCommand(from.toInt(), to.toInt(), amount.toInt())))


        it.response()
            .setStatusCode(202)
            .setStatusMessage("Transaction received")
            .putHeader("Content-Type","application/json")
            .end()
    }



}


