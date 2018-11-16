package infrastructure

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.eventbus.EventBus
import io.vertx.core.http.HttpServer
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext


class WebVerticle : AbstractVerticle() {
    lateinit var httpServer: HttpServer
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
        get("/account/:accountId/balance")
            .produces("application/json")
            .handler(getBalanceHandler)

        post("/transfer/:fromAccount/:amount/:toAccount")
            .consumes("application/json")
            .produces("application/json")
            .handler(transferHandler)

    }

//    private val handlerRoot = Handler<RoutingContext> { req ->
//        req.response().end("Welcome!")
//    }

    private val getBalanceHandler = Handler<RoutingContext> {req ->
        val params = req.request().params()
        val accountId: String = params.get("accountId")

        val query = JsonObject().put("accountId", accountId)
        eventBus.send<JsonObject>(QUERY_BALANCE, query) {
            if (it.succeeded())

                req.response()
                    .setStatusCode(200)
                    .putHeader("Content-Type", "application/json")
                    .end(it.result().body().encodePrettily())

            if(it.failed())
                req.response()
                    .setStatusCode(404)
                    .putHeader("Content-Type", "application/json")
                    .end(JsonObject().put("error", it.cause()).encodePrettily())
        }

    }

    private val transferHandler = Handler<RoutingContext> {
        val params = it.request().params()
        val from: String = params.get("fromAccount")
        val to: String = params.get("toAccount")
        val amount: String = params.get("amount")

        val command = mapOf("fromAccount" to from, "toAccount" to to, "moneyAmount" to amount)
        eventBus.send(TRANSFER_MONEY, JsonObject(command))
//        eventBus.send(infrastructure.TRANSFER_MONEY, JsonObject.mapFrom(TransferMoneyCommand(from.toInt(), to.toInt(), amount.toInt())))


        it.response()
            .setStatusCode(202)
            .setStatusMessage("Transaction received")
            .putHeader("Content-Type", "application/json")
            .end()
    }


}


