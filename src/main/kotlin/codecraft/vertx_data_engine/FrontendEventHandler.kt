package codecraft.vertx_data_engine

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject
import io.vertx.core.shareddata.AsyncMap
import io.vertx.core.shareddata.SharedData

class FrontendEventHandler(val bus: EventBus) : AbstractVerticle() {
  val eventBus = bus
  val consumer: MessageConsumer<JsonObject> = eventBus.consumer(KafkaConsumerVerticle.OWLSHOP_FRONTEND_EVENTS)
  lateinit var userHistory: AsyncMap<String, String>
  val sharedData: SharedData = vertx.sharedData()

  init {


  }

  override fun start() {
    val mapFuture: Future<AsyncMap<String, String>> = sharedData.getAsyncMap("userHistoryMap")
    mapFuture.onComplete({ res ->
      if (res.succeeded()) {
        userHistory = res.result();
      } else {
        // Something went wrong!
      }
    })
    consumer.handler({ message ->
      println("I have received a message: ${message.body().toString()}")
      userHistory.put(message.body().getString("body.id"), message.body().toString())
      println(userHistory.entries().toString())
    })
  }

  override fun stop() {
    super.stop()
  }
}
