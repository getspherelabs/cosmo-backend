package com.example.utils


object Faker {
    object Article {
        const val title = "Early Universe!"
        const val description = "The Redshift Wrangler project involves two main research tasks. Both involve “spectra”—plots that show how much power has arrived as a function of the light’s wavelength. In the first task, you’ll search for the bumps and wiggles in galaxy spectra that reveal the redshifts, and therefore distances, of galaxies."
        const val author = "Dr. Brown"
    }

    object Planet {
        object Mars {
            const val name = "Mars"
            const val description = "Mars is the fourth planet from the Sun – a dusty, cold, desert world with a very thin atmosphere."
            const val size = "144.4 million km"
            const val distanceFromSun = "249.31 million km"
            const val isPopular = true
        }

        object Saturn {
            const val name = "Saturn"
            const val description = "Saturn is the sixth planet from the Sun and the second-largest planet in our solar system. Like fellow gas giant Jupiter, Saturn is a massive ball made mostly of hydrogen and helium."
            const val size = "42.7 billion km²"
            const val distanceFromSun = "1.4622 billion km"
            const val isPopular = false
        }

    }

    object Star {

    }
}
