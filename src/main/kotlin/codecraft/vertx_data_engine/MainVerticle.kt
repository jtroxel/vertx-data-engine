package codecraft.vertx_data_engine

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.json.JsonObject
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager


class MainVerticle : AbstractVerticle() {

  init {

/*
*/
  }

  override fun start(startPromise: Promise<Void>) {

//    val config: JsonObject? = getConfig()

//    ClusterBootstrapDemo(vertx, "owl_hc_cluster_demo", 2).start()


    /*
        vertx
          .createHttpServer()
          .requestHandler { req ->
            req.response()
              .putHeader("content-type", "text/plain")
              .end("Hello from Vert.x!")
          }
          .listen(HTTP_PORT) { http ->
            if (http.succeeded()) {
              startPromise.complete()
              println("HTTP server started on port 8888")
            } else {
              startPromise.fail(http.cause());
            }
          }
    */
  }

}
