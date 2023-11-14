import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin ("jvm") version "1.7.21"
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "codecraft"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.4.6"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "codecraft.vertx_data_engine.MainVerticle"
val mainName = "codecraft.vertx_data_engine.AppKt"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
//  mainClass.set(launcherClassName)
  mainClass.set(mainName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-x86_64")
  implementation("io.vertx:vertx-config:$vertxVersion")
  implementation("io.vertx:vertx-config-yaml:$vertxVersion")
  implementation("io.vertx:vertx-hazelcast:$vertxVersion")
  implementation("io.vertx:vertx-json-schema:$vertxVersion")
  implementation("io.vertx:vertx-consul-client:$vertxVersion")
  implementation("io.vertx:vertx-kafka-client:$vertxVersion")
  implementation("io.vertx:vertx-opentracing:$vertxVersion")
  implementation("io.vertx:vertx-lang-kotlin:$vertxVersion")
  implementation("io.vertx:vertx-reactive-streams:$vertxVersion")
  implementation("org.slf4j:slf4j-api:1.7.29")
  implementation("org.slf4j:slf4j-log4j12:1.7.29")

  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.apache.camel:camel-core:4.0.2")
//  implementation("org.apache.camel:camel-stream:4.0.2")
//  implementation("org.apache.camel:camel-dsl:4.0.2")
//  implementation("org.apache.camel:camel-kotlin-dsl:4.0.2")
  implementation(kotlin("stdlib-jdk8"))
//  implementation("com.hazelcast.jet:hazelcast-jet:4.5.4")

  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")

}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

/*
tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
*/
