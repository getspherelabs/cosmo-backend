package com.example.route

import com.example.core.CosmoTest
import com.example.common.http.Status
import com.example.features.planet.PlanetRequest
import com.example.features.planet.PlanetIdResponse
import com.example.features.planet.PlanetResponse
import com.example.features.planet.PlanetsResponse
import com.example.utils.*
import org.junit.Test
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PlanetRouteTest : CosmoTest() {

    @Test
    fun `when the planet insert and check the response is success`() = withApp {
        val planetName = Faker.Planet.Mars.name
        val desc = Faker.Planet.Mars.description
        val isPopular = Faker.Planet.Mars.isPopular
        val size = Faker.Planet.Mars.size
        val distanceFromSun = Faker.Planet.Mars.distanceFromSun

        val request = PlanetRequest(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        ).toJson()

        post("$v1/planet/new", request).content.toDto<PlanetIdResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertNotNull(newResponse.id)
        }
    }

    @Test
    fun `when the planet insert and get planets`() = withApp {
        val planetName = Faker.Planet.Mars.name
        val desc = Faker.Planet.Mars.description
        val isPopular = Faker.Planet.Mars.isPopular
        val size = Faker.Planet.Mars.size
        val distanceFromSun = Faker.Planet.Mars.distanceFromSun

        val request = PlanetRequest(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        ).toJson()

        post("$v1/planet/new", request)

        get("$v1/planets").content.toDto<PlanetsResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertEquals(newResponse.planets.first().name, planetName)
        }
    }

    @Test
    fun `when user insert planet and check getting planet by id`() = withApp {
        val planetName = Faker.Planet.Mars.name
        val desc = Faker.Planet.Mars.description
        val isPopular = Faker.Planet.Mars.isPopular
        val size = Faker.Planet.Mars.size
        val distanceFromSun = Faker.Planet.Mars.distanceFromSun

        val request = PlanetRequest(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        ).toJson()

        val newRequest = post("api/v1/planet/new", request).content.toDto<PlanetIdResponse>()

        get("$v1/planet/${newRequest.id}").content.toDto<PlanetResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertEquals(newResponse.planet?.name, planetName)
        }
    }

    @Test
    fun `when user insert planet and check getting planet by id when it is not found`() = withApp {
        val id = UUID.randomUUID().toString()

        get("$v1/planet/$id").content.toDto<PlanetResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.NotFound)
            assertNull(newResponse.planet)
        }
    }

    @Test
    fun `when user insert planets and search by name planets`() = withApp {
        val planetName = Faker.Planet.Mars.name
        val desc = Faker.Planet.Mars.description
        val isPopular = Faker.Planet.Mars.isPopular
        val size = Faker.Planet.Mars.size
        val distanceFromSun = Faker.Planet.Mars.distanceFromSun

        val request = PlanetRequest(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        ).toJson()

        post("$v1/planet/new", request)

        get("$v1/planets/$planetName").content.toDto<PlanetsResponse>().let { newResponse ->
            assertEquals(newResponse.status, Status.Success)
            assertEquals(newResponse.planets.first().name, planetName)
        }
    }
    companion object {
        private const val v1 = "/api/v1"
    }
}