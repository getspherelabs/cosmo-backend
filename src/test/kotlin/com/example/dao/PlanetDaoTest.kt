package com.example.dao

import com.example.core.CosmoTest
import com.example.data.dao.PlanetDao
import io.github.serpro69.kfaker.faker
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class PlanetDaoTest : CosmoTest() {


    private val planetDao: PlanetDao by inject()
    private val faker = faker { }

    @Test
    fun `should insert planets and verify counts`() = withApp {
        val planetName = faker.space.planet()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        planetDao.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )
        planetDao.insert(
            name = planetName,
            description = desc,
            isPopular = isPopular,
            size = size,
            distanceFromSun = distanceFromSun
        )

        val planetCounts = planetDao.getPlanetCounts()

        assertEquals(2, planetCounts)
    }

    @Test
    fun `should insert planet and get planets`() = withApp { }

}