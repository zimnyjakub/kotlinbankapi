package com.zimny.kotlinbank.api.infrastructure

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class APIVertice : AbstractVerticle() {
    lateinit var httpServer : HttpServer

    override fun start(startFuture: Future<Void>) {
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
        get("/").handler(handlerRoot)
    }

    private val handlerRoot = Handler<RoutingContext> { req ->
        req.response().end("Welcome!")
    }
}


