package com.example.controller

import com.example.core.CosmoTest
import com.example.http.request.PlanetRequest
import com.example.http.response.PlanetIdResponse
import com.example.http.response.PlanetResponse
import com.example.http.response.PlanetsResponse
import io.github.serpro69.kfaker.faker
import org.junit.Test
import org.koin.test.inject
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PlanetControllerTest : CosmoTest() {

    private val planetController: PlanetController by inject()
    private val faker = faker { }

    @Test
    fun `should add planet and check response is successful`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        val response = planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = isPopular,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        assertEquals(PlanetIdResponse.onSuccess(checkNotNull(response.id)), response)
    }

    @Test
    fun `should insert planet and check response get planets`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = isPopular,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )
        planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = isPopular,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        val response = planetController.getPlanets()

        assertEquals(2, response.planets.size)
        assertEquals(PlanetsResponse.onSuccess(response.planets), response)
    }

    @Test
    fun `should insert planet and check remove all if there are not planets`() = withApp {
        planetController.removeAll()
        val response = planetController.getPlanets()
        assertEquals(0, response.planets.size)
    }

    @Test
    fun `should insert planet and check deleting by id if it gives a wrong uuid`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = isPopular,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        val response = planetController.deletePlanetById(UUID.randomUUID().toString())

        assertEquals("Not exist planet", response.message)
    }

    @Test
    fun `should insert planets and check response popular planets`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val size = "100"
        val distanceFromSun = "1001km"

        planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = true,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = false,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = true,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        val response = planetController.getPopularPlanets()

        assertEquals(2, response.planets.size)
        assertEquals(PlanetsResponse.onSuccess(response.planets), response)
    }

    @Test
    fun `should insert planets and check response update planet`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val size = "100"
        val distanceFromSun = "1001km"

        val planetId = planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = true,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        val response = planetController.updatePlanet(
            planetId.id.toString(), PlanetRequest(
                name = "Mars",
                description = faker.quote.famousLastWords(),
                size = "150",
                distanceFromSun = "120km"
            )
        )

        assertEquals(PlanetIdResponse.onSuccess(response.id.toString()), response)
    }

    @Test
    fun `should insert planets and check response get by id`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val size = "100"
        val distanceFromSun = "1001km"

        val planetId = planetController.addPlanet(
            PlanetRequest(
                planetName,
                desc,
                isPopular = true,
                size = size,
                distanceFromSun = distanceFromSun
            )
        )

        val response = planetController.getPlanetById(planetId.id.toString())

        assertEquals(PlanetResponse.onSuccess(checkNotNull(response.planet)), response)
        assertNotNull(response.planet != null)
    }
}