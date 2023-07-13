val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.21"
    id("io.ktor.plugin") version "2.3.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "cosmo-backend"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}



repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation(Libs.Ktor.negotiation)
    implementation(Libs.Ktor.core)
    implementation(Libs.Ktor.json)
    implementation(Libs.Ktor.cors)
    implementation(Libs.Ktor.netty)
    implementation(Libs.Ktor.openApi)
    implementation(Libs.Ktor.swaggerUi)
    implementation(Libs.Exposed.core)
    implementation(Libs.Exposed.dao)
    implementation(Libs.Exposed.jdbc)
    implementation(Libs.Exposed.jodatime)
    implementation(Libs.Postgresql.core)
    implementation(Libs.Hikari.core)
    implementation(Libs.H2.h2)
    implementation(Libs.Koin.ktor)
    implementation(Libs.Koin.core)
    implementation(Libs.Faker.kotlin)

    testImplementation(Libs.Koin.test)
    testImplementation(Libs.Hikari.core)
    testImplementation(Libs.Ktor.test)
    testImplementation(Libs.H2.h2)
    testImplementation(Libs.Coroutine.test)

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}