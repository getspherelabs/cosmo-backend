
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.21"
    id("io.ktor.plugin") version "2.3.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("org.flywaydb.flyway") version "8.5.4"
}



application {
    group = "com.example.cosmo"
    version = "0.3.0"
    mainClass.set("io.ktor.server.netty.EngineMain")
}


tasks {
    create("stage").dependsOn("installDist")
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://jitpack.io")
    maven(url = "https://kotlin.bintray.com/ktor")
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
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
    implementation(Libs.Flyway.core)

    testImplementation(Libs.Koin.test)
    testImplementation(Libs.Hikari.core)
    testImplementation(Libs.Ktor.test)
    testImplementation(Libs.H2.h2)
    testImplementation(Libs.Coroutine.test)
    implementation("io.ktor:ktor-server-config-yaml:2.3.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

flyway {
    url = System.getenv("DATABASE_URL")
    user = System.getenv("PGUSER")
    password = System.getenv("PGPASSWORD")
    baselineOnMigrate = true
    locations = arrayOf("filesystem:resources/db/migrations")
}
