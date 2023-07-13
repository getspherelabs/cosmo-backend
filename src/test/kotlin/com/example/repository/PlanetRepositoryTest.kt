package com.example.repository

import com.example.core.CosmoTest
import com.example.features.planet.PlanetRepository
import com.example.utils.Faker
import org.junit.Test
import org.koin.test.inject
import java.util.*
import kotlin.test.assertEquals

class PlanetRepositoryTest : CosmoTest() {

    private val planetRepository: PlanetRepository by inject()

    @Test
    fun `should insert planets and verify counts`() = withApp {
        planetRepository.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )
        planetRepository.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val planetCounts = planetRepository.getPlanetCounts()

        assertEquals(2, planetCounts)
    }

    @Test
    fun `should insert planet and get planets`() = withApp {
        planetRepository.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        val planets = planetRepository.getPlanets()

        assertEquals(Faker.Planet.Mars.description, planets.first().description)
    }

    @Test
    fun `should insert planets and remove all`() = withApp {
        planetRepository.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )
        planetRepository.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        planetRepository.removeAll()

        val planetCounts = planetRepository.getPlanetCounts()

        assertEquals(0, planetCounts)
    }

    @Test
    fun `should insert planets and get by id`() = withApp {
        planetRepository.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )
        planetRepository.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val firstPlanet = planetRepository.getPlanets().first()
        val newPlanet = planetRepository.getPlanetById(firstPlanet.id)

        assertEquals(newPlanet, firstPlanet)
    }

    @Test
    fun `should insert planet and check id if it is true`() = withApp {
        planetRepository.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )
        planetRepository.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val firstPlanet = planetRepository.getPlanets().first()
        val exists = planetRepository.exists(firstPlanet.id)

        assertEquals(true, exists)
    }

    @Test
    fun `should insert planet and check id if it is false`() = withApp {
        planetRepository.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )
        planetRepository.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val exists = planetRepository.exists(UUID.randomUUID().toString())

        assertEquals(false, exists)
    }

    @Test
    fun `should insert planets and delete by id`() = withApp {
        planetRepository.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )
        planetRepository.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val firstPlanet = planetRepository.getPlanets().first()
        val result = planetRepository.deleteById(firstPlanet.id)
        val planetCounts = planetRepository.getPlanetCounts()

        assertEquals(true, result)
        assertEquals(1, planetCounts)
    }

}