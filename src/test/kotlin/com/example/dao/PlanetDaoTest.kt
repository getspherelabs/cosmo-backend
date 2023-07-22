package com.example.dao

import com.example.core.CosmoTest
import com.example.data.dao.PlanetDao
import com.example.utils.Faker
import org.junit.Test
import org.koin.test.inject
import java.util.UUID
import kotlin.test.assertEquals

class PlanetDaoTest : CosmoTest() {


    private val planetDao: PlanetDao by inject()

    @Test
    fun `should insert planets and verify counts`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        planetDao.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val planetCounts = planetDao.getPlanetCounts()

        assertEquals(2, planetCounts)
    }

    @Test
    fun `should insert planet and get planets`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        val planets = planetDao.getPlanets()

        assertEquals(Faker.Planet.Mars.description, planets.first().description)
    }

    @Test
    fun `should insert planets and remove all`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        planetDao.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        planetDao.removeAll()

        val planetCounts = planetDao.getPlanetCounts()

        assertEquals(0, planetCounts)
    }

    @Test
    fun `should insert planets and get by id`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        planetDao.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val firstPlanet = planetDao.getPlanets().first()
        val newPlanet = planetDao.getPlanetById(firstPlanet.id)

        assertEquals(newPlanet, firstPlanet)
    }

    @Test
    fun `should insert planet and check id if it is true`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        planetDao.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val firstPlanet = planetDao.getPlanets().first()
        val exists = planetDao.exists(firstPlanet.id)

        assertEquals(true, exists)
    }

    @Test
    fun `should insert planet and check id if it is false`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        planetDao.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val exists = planetDao.exists(UUID.randomUUID().toString())

        assertEquals(false, exists)
    }

    @Test
    fun `should insert planets and delete by id`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        planetDao.insert(
            name = Faker.Planet.Saturn.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val firstPlanet = planetDao.getPlanets().first()
        val result = planetDao.deleteById(firstPlanet.id)
        val planetCounts = planetDao.getPlanetCounts()

        assertEquals(true, result)
        assertEquals(1, planetCounts)
    }

    @Test
    fun `should insert planets and search by name`() = withApp {
        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Mars.description,
            isPopular = Faker.Planet.Mars.isPopular,
            size = Faker.Planet.Mars.size,
            distanceFromSun = Faker.Planet.Mars.distanceFromSun
        )

        planetDao.insert(
            name = Faker.Planet.Mars.name,
            description = Faker.Planet.Saturn.description,
            isPopular = Faker.Planet.Saturn.isPopular,
            size = Faker.Planet.Saturn.size,
            distanceFromSun = Faker.Planet.Saturn.distanceFromSun
        )

        val planets = planetDao.searchPlanets("ma")
        println(planets)
        assertEquals(2, planets.size)
        assertEquals(Faker.Planet.Mars.name, planets.first().name)
    }
}