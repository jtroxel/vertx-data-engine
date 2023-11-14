package codecraft.vertx_data_engine

import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject

const val HTTP_PORT = 6789

class App {

  var vertx: Vertx = Vertx.vertx()

  // TODO get from config file
  val kafkaConsumerConfig = JsonObject(
    hashMapOf(
      "config" to hashMapOf(
        "client.id" to "data-engine",
        "auto.offset.reset" to "earliest",
        "bootstrap.servers" to "localhost:19092",
        "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
        "value.deserializer" to "io.vertx.kafka.client.serialization.JsonObjectDeserializer"
      )
    ) as Map<String, Any>?
  )


  init {
/*
    val conf = Config()
    conf.networkConfig.setReuseAddress(true)
    val hzMgr = HazelcastClusterManager(conf)
    val vertxOptions: VertxOptions = VertxOptions().setClusterManager(hzMgr);
    Vertx
      .clusteredVertx(vertxOptions)
      .onComplete({ res ->
        if (res.succeeded()) {
          vertx = res.result();
        } else {
          // failed!
        }
      })
*/

//    val kafkaConsumerConfig = getFileConfig(...)


  }

  fun start() {
    vertx.deployVerticle(OrderHandler(vertx))
    vertx.deployVerticle(KafkaConsumerVerticle(vertx, kafkaConsumerConfig))

  }
  /*
    private fun getFileConfig(): JsonObject? {
      val store: ConfigStoreOptions = ConfigStoreOptions()
        .setType("file")
        .setFormat("yaml")
        .setConfig(
          JsonObject()
            .put("path", "my-config.yaml")
        );

      var config: JsonObject? = null
      ConfigRetriever.create(
        vertx,
        ConfigRetrieverOptions().addStore(store)
      )
        .getConfig().onComplete({ ar ->
          if (ar.failed()) {
            // Failed to retrieve the configuration
          } else {
            config = ar.result()
          }
        })
      return config
    }
  */
}

fun main() {
  App().start()
}
