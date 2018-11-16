package unit

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import domain.InMemoryAccountDataStore
import domain.RequestBalanceQueryHandler
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test


//todo ommitting intentionally due to lack of resources. this is partially tested in the domain model
//todo read up vert.x documentation and implement their tests rather than JUnit

//class RequestBalanceQueryHandlerTests {
//    @Before
//    fun cleanDataStore() {
//        InMemoryAccountDataStore.instance.clear()
//    }
//
//    @Test
//    fun whenAccountDoesNotExistResultShouldBeFail() {
//        val balanceQueryHandler = RequestBalanceQueryHandler()
//        val msg= mock<Message<JsonObject>> {
//            on { body() } doReturn(JsonObject().put("accountId", 1))
//        }
//
//       balanceQueryHandler.handle(msg)
//
//    }
//
//}
