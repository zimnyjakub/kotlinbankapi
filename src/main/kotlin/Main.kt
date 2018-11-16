import infrastructure.BackendVerticle
import infrastructure.WebVerticle
import io.vertx.core.Vertx

fun main(args : Array<String>) {
    val vertx: Vertx = Vertx.vertx()

    vertx.deployVerticle(WebVerticle())
    vertx.deployVerticle(BackendVerticle())

}

