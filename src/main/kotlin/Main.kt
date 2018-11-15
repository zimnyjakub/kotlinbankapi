import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.vertx.core.Vertx

fun main(args : Array<String>) {
    val vertx: Vertx = Vertx.vertx()

    vertx.deployVerticle(WebVerticle())
    vertx.deployVerticle(BackendVerticle())

}

