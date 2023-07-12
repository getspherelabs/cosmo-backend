object Version {
    const val exposed = "0.40.1"
    const val postgresql = "42.5.4"
    const val hikari = "4.0.2"
    const val koin = "3.4.1"
    const val h2 = "2.1.214"
    const val faker = "1.14.0"
    const val ktor = "2.3.1"
    const val coroutineTest = "1.7.2"
    const val testContainer = "1.18.3"
}

object Libs {
    object Ktor {
        const val negotiation = "io.ktor:ktor-server-content-negotiation-jvm:${Version.ktor}"
        const val core = "io.ktor:ktor-server-core-jvm:${Version.ktor}"
        const val test = "io.ktor:ktor-server-tests-jvm:${Version.ktor}"
        const val testHost = "io.ktor:ktor-server-test-host:${Version.ktor}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json-jvm:${Version.ktor}"
        const val netty = "io.ktor:ktor-server-netty-jvm:${Version.ktor}"
        const val cors = "io.ktor:ktor-server-cors:${Version.ktor}"
        const val swagger = "io.ktor:ktor-server-swagger:${Version.ktor}"
        const val openApi = "io.ktor:ktor-server-openapi:${Version.ktor}"
        const val swaggerUi = "io.github.smiley4:ktor-swagger-ui:2.2.0"
    }

    object Exposed {
        const val core = "org.jetbrains.exposed:exposed-core:${Version.exposed}"
        const val dao = "org.jetbrains.exposed:exposed-dao:${Version.exposed}"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:${Version.exposed}"
        const val jodatime = "org.jetbrains.exposed:exposed-jodatime:${Version.exposed}"
    }

    object Postgresql {
        const val core = "org.postgresql:postgresql:${Version.postgresql}"
    }

    object Hikari {
        const val core = "com.zaxxer:HikariCP:${Version.hikari}"
    }

    object Koin {
        const val ktor = "io.insert-koin:koin-ktor:${Version.koin}"
        const val test = "io.insert-koin:koin-test:${Version.koin}"
        const val core = "io.insert-koin:koin-core:3.4.2"
    }

    object H2 {
        const val h2 = "com.h2database:h2:${Version.h2}"
    }

    object Faker {
        const val kotlin = "io.github.serpro69:kotlin-faker:${Version.faker}"
    }

    object Coroutine {
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$${Version.coroutineTest}"
    }

    object TestContainers {
        const val core = "org.testcontainers:testcontainers:${Version.testContainer}"
        const val junit = "org.testcontainers:junit-jupiter:${Version.testContainer}"
        const val postgres = "org.testcontainers:postgresql:${Version.testContainer}"
    }
}