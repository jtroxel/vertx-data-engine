package codecraft.vertx_data_engine

import io.vertx.core.json.JsonObject
import java.io.Serializable

data class KafkaRecordMap(
  val key: String?, val body: JsonObject, val headers: JsonObject?,
  val meta: JsonObject?
): Serializable
