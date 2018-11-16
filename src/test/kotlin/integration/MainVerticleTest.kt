import infrastructure.MainVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.junit.VertxUnitRunner
import io.vertx.ext.web.client.WebClient
import org.junit.*
import org.junit.runner.RunWith

@RunWith(VertxUnitRunner::class)
class MainVerticleTest {
    companion object {
        private var vertx: Vertx? = null
        @BeforeClass
        @JvmStatic
        fun setUp(tc: TestContext) {
            vertx = Vertx.vertx()
            vertx!!.deployVerticle(MainVerticle::class.java!!.getName(), tc.asyncAssertSuccess())
        }

        @AfterClass
        @JvmStatic
        fun tearDown(tc: TestContext) {
            vertx!!.close(tc.asyncAssertSuccess())
        }
    }



    @Test
    fun testThatTheServerIsStarted(tc: TestContext) {
        val async = tc.async()
        vertx!!.createHttpClient().getNow(8000, "localhost", "/") { response ->
            tc.assertEquals(response.statusCode(), 404)
            response.bodyHandler { body ->
                tc.assertTrue(body.length() > 0)
                async.complete()
            }
        }
    }

    @Test
    fun testThatStartingAccountBalanceIs1000(tc: TestContext) {
        val async = tc.async()
        vertx!!.createHttpClient().getNow(8000, "localhost", "/account/321/balance") { response ->
            tc.assertEquals(response.statusCode(), 200)
            response.bodyHandler { body ->
                tc.assertEquals(body.toJsonObject().getInteger("amount"), 1000)
                async.complete()
            }
        }
    }

    @Test
    fun testThatTransferAmountDoesCorrectJob(tc: TestContext) {
        val async = tc.async()
        val httpClient = vertx!!.createHttpClient()

        val client = WebClient.wrap(httpClient)

        client.post(8000, "localhost", "/transfer/1/200/2")
            .putHeader("Content-Type", "application/json")
            .send {
                tc.assertTrue(it.succeeded())
                tc.assertEquals(it.result().statusCode(), 202)
            }


        Thread.sleep(1000)


        httpClient.getNow(8000, "localhost", "/account/1/balance") { response ->
            tc.assertEquals(response.statusCode(), 200)
            response.bodyHandler { body ->
                tc.assertEquals(body.toJsonObject().getInteger("amount"), 800)
                async.complete()
            }
        }

    }
}