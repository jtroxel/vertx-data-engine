package codecraft.vertx_data_engine

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject
import io.vertx.core.shareddata.AsyncMap
import io.vertx.core.shareddata.SharedData
import org.slf4j.LoggerFactory.getLogger

class OrderHandler(vertx: Vertx) : AbstractVerticle() {
  val eventBus: EventBus = vertx.eventBus()
  val consumer: MessageConsumer<KafkaRecordMap> = eventBus.consumer(KafkaConsumerVerticle.OWLSHOP_ORDERS)
  var orderMap: AsyncMap<String, AsyncMap<String, JsonObject>>?
  val sharedData: SharedData = vertx.sharedData()

  init {
    orderMap = getAsynchMap("orderMap")
  }

  companion object {
    @Suppress("JAVA_CLASS_ON_COMPANION")
    @JvmStatic
    private val logger = getLogger(javaClass.enclosingClass)

  }

  override fun start() {
    consumer.handler { message ->
      val orderId = message.body().body.getString("body.id")
      logger.info("I have received a message: " + message.body().body.getString("correlationId"))

      var orderHistory: AsyncMap<String, JsonObject>
      orderMap?.get(orderId)?.onComplete({ resGet ->
        if (resGet.succeeded()) {
          // Successfully got the value
          orderHistory = resGet.result()
          orderHistory.put(message.body().meta?.getString("record_time"), message.body().body)
        } else {
          val mapFuture: Future<AsyncMap<String, JsonObject>> = sharedData.getAsyncMap(orderId)
          mapFuture.onComplete { res ->
            if (res.succeeded()) {
              orderHistory = res.result();
              orderHistory.put(message.body().meta?.getString("record_time"), message.body().body)
            } else {
              // Something went wrong!
            }
          }
        }
      })
      println("Updated order history: " + orderMap?.get(orderId))
    }
  }

  override fun stop() {
    super.stop()
  }

  fun getAsynchMap(mapId: String): AsyncMap<String, AsyncMap<String, JsonObject>>? {
    val mapFuture: Future<AsyncMap<String, AsyncMap<String, JsonObject>>> = sharedData.getAsyncMap(mapId)
    var map: AsyncMap<String, AsyncMap<String, JsonObject>>? = null
    mapFuture.onComplete { res ->
      if (res.succeeded()) {
        map = res.result();
      } else {
        // Something went wrong!
      }
    }
    return map
  }

}
