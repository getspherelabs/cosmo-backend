package com.example.route

import com.example.core.CosmoTest
import com.example.http.Status
import com.example.http.request.PlanetRequest
import com.example.http.response.PlanetIdResponse
import com.example.http.response.PlanetResponse
import com.example.http.response.PlanetsResponse
import com.example.utils.*
import io.github.serpro69.kfaker.faker
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PlanetRouteTest : CosmoTest() {

    private val faker = faker { }

    @Test
    fun `when the planet insert and check the response is success`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        val request = PlanetRequest(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        ).toJson()

        post("api/v1/planet/new", request).content.toDto<PlanetIdResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertNotNull(newResponse.id)
        }
    }

    @Test
    fun `when the planet insert and get planets`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        val request = PlanetRequest(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        ).toJson()

        post("api/v1/planet/new", request)

        get("/api/v1/planets").content.toDto<PlanetsResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertEquals(newResponse.planets.first().name, planetName)
        }
    }
}