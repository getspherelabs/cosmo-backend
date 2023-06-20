object Version {
    const val exposed = "0.40.1"
    const val postgresql = "42.5.4"
    const val hikari = "2.3.13"
    const val koin = "3.3.1"
}

object Libs {
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
    }
}