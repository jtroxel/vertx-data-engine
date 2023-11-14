package codecraft.vertx_data_engine

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager

class ClusterBootstrapDemo(vtx: Vertx, clusterName: String, sz: Int) {
  val vertx = vtx
  val size = sz
  val clusterConfig: Config = Config();
  var clusterNodes = mutableListOf<String>()

  init {
    clusterConfig.setClusterName(clusterName)
/*
    val options: VertxOptions = VertxOptions().setClusterManager(HazelcastClusterManager());

    Vertx
      .clusteredVertx(options)
      .onComplete({ res ->
        if (res.succeeded()) {
          vertx = res.result();
        } else {
          // failed!
        }
      })
*/
  }

  fun start() {
    vertx.executeBlocking({ ->

      for (c in 1..size) {
        val hci = Hazelcast.newHazelcastInstance(clusterConfig)
        println("Starting hc node $c ${hci.name}")
        clusterNodes.add(hci.name)
      }
    })
  }


}
