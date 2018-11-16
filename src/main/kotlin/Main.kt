import infrastructure.MainVerticle
import io.vertx.core.Vertx

fun main(args : Array<String>) {
    val vertx: Vertx = Vertx.vertx()

    vertx.deployVerticle(MainVerticle())

}

