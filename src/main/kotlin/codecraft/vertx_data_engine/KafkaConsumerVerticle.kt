package codecraft.vertx_data_engine

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.JsonObject
import io.vertx.kafka.client.common.KafkaClientOptions
import io.vertx.kafka.client.consumer.KafkaConsumer
import io.vertx.kafka.client.consumer.KafkaConsumerRecord
import io.vertx.kotlin.core.json.get
import io.vertx.kotlin.core.json.jsonObjectOf
import org.slf4j.LoggerFactory.getLogger
import java.time.Instant.now

class KafkaConsumerVerticle(vertx: Vertx, config: JsonObject?) : AbstractVerticle() {

  val eventBus: EventBus = vertx.eventBus()
  val kafkaOptions = config


  companion object {
    val OWLSHOP_FRONTEND_EVENTS = "owlshop-frontend-events"
    val OWLSHOP_ORDERS = "owlshop-orders"

    @Suppress("JAVA_CLASS_ON_COMPANION")
    @JvmStatic
    private val logger = getLogger(javaClass.enclosingClass)

  }

  init {
  }

  private val MESSAGE_CHANNELS: List<String> = listOf(OWLSHOP_ORDERS)

  /**
   * Start the verticle, which starts Kafka Consumers for each topic
   */
  override fun start() {

    // Handle new messages (in a single thread)

    for (ch in MESSAGE_CHANNELS) {
      kafkaOptions?.get<JsonObject>("config")?.put("group.id", "dde-kafka-relay-$ch")
      var kafkaConsumer: KafkaConsumer<String, JsonObject> =
        KafkaConsumer.create(vertx, KafkaClientOptions(kafkaOptions))


      // Subscribe to the topic
      kafkaConsumer.subscribe(ch)
        .onSuccess({ v ->
          logger.info("subscribed to $ch")
          kafkaConsumer.handler(relayHandler)

        }).onFailure({ cause ->
          System.out.println("Could not subscribe to $ch" + cause.message)
        })
    }


  }

    /**
   * Relays inbound topics to the vert.x bus
   */
  val relayHandler: (event: KafkaConsumerRecord<String, JsonObject>) -> Unit = { record ->
    logger.info("Consuming Kafka record key=${record.key()} from ${record.topic()}")
//    println("Consuming Kafka record key=" + record.key())
//          + ",value=" + record.value() +
//          ",partition=" + record.partition() + ",offset=" + record.offset()

//        forward to an internal channel of the same name
    eventBus.publish(
      record.topic(), KafkaRecordMap(
        key = record.key(),
        body = record.value(),
        headers = jsonObjectOf(*record.headers().map { kp -> Pair(kp.key(), kp.value()) }.toTypedArray()),
        meta = jsonObjectOf(
          "topic" to record.topic(),
          "partition" to record.partition(),
          "record_time" to record.timestamp()
        )
      )
    )
  }

}
