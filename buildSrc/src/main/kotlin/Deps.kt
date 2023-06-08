object Version {
    const val exposed = "0.40.1"
    const val postgresql = "42.5.4"
}

object Libs {
    object Exposed {
        const val core = "org.jetbrains.exposed:exposed-core:${Version.exposed}"
        const val dao = "org.jetbrains.exposed:exposed-dao:${Version.exposed}"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:${Version.exposed}"
    }

    object Postgresql {
        const val core = "org.postgresql:postgresql:${Version.postgresql}"
    }
}