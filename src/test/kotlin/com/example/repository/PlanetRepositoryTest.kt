package com.example.repository

import com.example.core.CosmoTest
import com.example.features.planet.PlanetRepository
import io.github.serpro69.kfaker.faker
import org.junit.Test
import org.koin.test.inject
import java.util.*
import kotlin.test.assertEquals

class PlanetRepositoryTest: CosmoTest() {

    private val planetRepository: PlanetRepository by inject()
    private val faker = faker { }

    @Test
    fun `should insert planets and verify counts`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        val planetCounts = planetRepository.getPlanetCounts()

        assertEquals(2, planetCounts)
    }

    @Test
    fun `should insert planet and get planets`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        val planets = planetRepository.getPlanets()

        assertEquals(desc, planets.first().description)
    }

    @Test
    fun `should insert planets and remove all`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        planetRepository.removeAll()

        val planetCounts = planetRepository.getPlanetCounts()

        assertEquals(0, planetCounts)
    }

    @Test
    fun `should insert planets and get by id`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        val firstPlanet = planetRepository.getPlanets().first()
        val newPlanet = planetRepository.getPlanetById(firstPlanet.id)

        assertEquals(newPlanet, firstPlanet)
    }

    @Test
    fun `should insert planet and check id if it is true`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        val firstPlanet = planetRepository.getPlanets().first()
        val exists = planetRepository.exists(firstPlanet.id)

        assertEquals(true, exists)
    }

    @Test
    fun `should insert planet and check id if it is false`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        val exists = planetRepository.exists(UUID.randomUUID().toString())

        assertEquals(false, exists)
    }

    @Test
    fun `should insert planets and delete by id`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetRepository.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        val firstPlanet = planetRepository.getPlanets().first()
        val result = planetRepository.deleteById(firstPlanet.id)
        val planetCounts = planetRepository.getPlanetCounts()

        assertEquals(true, result)
        assertEquals(1, planetCounts)
    }

}